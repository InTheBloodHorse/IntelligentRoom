package cn.lsu.chicken.room.shell;

import lombok.Data;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

@Data
public class MyBatisGen {
    private String entity;
    private String dao;
    private String mapper;
    // 分页参数
    private String page = "page";
    private String size = "size";
    private String offset = "offset";
    private String mybatisConfig = "classpath:mybatis-generator.xml";
    private String project = System.getProperty("user.dir") + "/";


    public void gen() {
        readMyBatisConfig();
        addAnnotationLombok();
        addAnnotationComponent();
        addMapperXML();
    }

    private void readMyBatisConfig() {
        String xmlContent = null;
        try {
            xmlContent = readResourceFile(mybatisConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setVariable("entity", "javaModelGenerator", xmlContent);
        setVariable("dao", "javaClientGenerator", xmlContent);
        setVariable("mapper", "sqlMapGenerator", xmlContent);
    }

    /*
        实体类加上@Data注解
     */
    private void addAnnotationLombok() {
        String entityPath = entity;
        File file = new File(entityPath);
        File[] fs = file.listFiles();
        for (File f : fs) {
            String preFileName = f.getName().split("\\.")[0];
            if (!preFileName.endsWith("Example")) {
                addAnnotationData(f.getAbsolutePath());
            }
        }
        System.out.println("添加@Data注解成功");

    }

    private void addAnnotationData(String path) {
        String content = null;
        try {
            content = readFileToString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = "import lombok.Data;\n\n@Data\n";
        if (content.indexOf(data.split(";")[0]) != -1) {
            return;
        }
        Integer index = content.indexOf("public");
        String prefixContent = content.substring(0, index);
        String suffixContent = content.substring(index);
        content = prefixContent + data + suffixContent;
        writeStringToFile(path, content);
    }

    private void addAnnotationComponent() {
        String daoPath = dao;
        File file = new File(daoPath);
        File[] fs = file.listFiles();
        for (File f : fs) {
            addAnnotationComponentAt(f.getAbsolutePath());
        }
        System.out.println("添加@Component注解成功");

    }

    private void addAnnotationComponentAt(String path) {
        String content = null;
        try {
            content = readFileToString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = "import org.springframework.stereotype.Component;\n\n@Component\n";
        if (content.indexOf(data.split(";")[0]) != -1) {
            return;
        }
        Integer index = content.indexOf("public");
        String prefixContent = content.substring(0, index);
        String suffixContent = content.substring(index);
        content = prefixContent + data + suffixContent;
        writeStringToFile(path, content);
    }

    private void addMapperXML() {
        String mapperPath = mapper;
        File file = new File(mapperPath);
        File[] fs = file.listFiles();
        for (File f : fs) {
            addPageXML(f.getAbsolutePath());
        }
        System.out.println("添加ExamplePage成功");
    }

    private void addPageXML(String path) {
        String content = null;
        try {
            content = readFileToString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String page = "<if test=\"" + getPage() + " != null and " + getSize() + "!= null\">\n" +
                "            limit #{" + getOffset() + "},#{" + getSize() + "}\n" +
                "        </if>\n";
        Integer pre = -1;
        String need = "${orderByClause}";
        while (true) {
            Integer index = content.indexOf(need, pre + need.length());
            if (index == -1 || index == pre) {
                break;
            }
            pre = index;
            if (content.indexOf("limit #{offset},#{size}", index) != -1) {
                continue;
            }
            Integer startIndex = content.indexOf(">", index);
            content = content.substring(0, startIndex + 1) + "\n" + page + content.substring(startIndex + 1);
        }
        writeStringToFile(path, content);
    }

    private void setVariable(String variableName, String xmlId, String content) {
        Integer javaModelGenerator = content.indexOf(xmlId);
        Integer globalLen = " targetPackage=\"".length();
        Integer startIndex = javaModelGenerator + xmlId.length() + globalLen;
        Integer endIndex = content.indexOf("\"", startIndex);
        String value = String.join("/", content.substring(startIndex, endIndex).split("\\."));

        Integer preStartIndex = endIndex + 1 + globalLen;
        Integer preEndIndex = content.indexOf("\"", preStartIndex);
        String prefix = content.substring(preStartIndex, preEndIndex);
        setVariableByReflect(variableName, project + prefix + "/" + value);
    }


    // 反射设置变量
    private void setVariableByReflect(String variableName, String value) {
        Class<?> classType = this.getClass();
        Field variable = null;
        try {
            variable = classType.getDeclaredField(variableName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        variable.setAccessible(true);
        try {
            variable.set(this, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // 读取resource文件内容
    private static String readResourceFile(String path) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        InputStream inputStream = null;
        try {
            inputStream = resourceLoader.getResource(path).getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());
    }

    // 读取文件转成String
    private static String readFileToString(String path) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
        byte[] bytes = new byte[2048];
        int n = -1;
        StringBuilder stringBuilder = new StringBuilder();
        while ((n = in.read(bytes, 0, bytes.length)) != -1) {
            String str = new String(bytes, 0, n, "GBK");
            stringBuilder.append(str);
        }
        in.close();
        return stringBuilder.toString();
    }

    // 将String写入文件
    private static void writeStringToFile(String path, String content) {
        File file = new File(path);
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        printStream.println(content);
    }
}
