import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @description:
 * @author:Shi Xiaohao
 * @e-mail:sfturing@gmail.com
 * @date :2017/12/3 上午11:09
 */
public class ReadResourse {

    private static final Logger log = LoggerFactory.getLogger(ReadResourse.class);


    /**
     * @ClassName:ReadResourse
     * @Description:根据路径，读取资源文件
     * @Author:Shi Xiaohao
     * @E-mail:sfturing@gmail.com
     * @Date :2017/12/3 上午11:13
     */
    public  List<String> readFile() {
        File file = getTXTFile();
        String encoding = "UTF-8";
        String lineInfo = "";
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        List<String> listStr = new LinkedList<String>();
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(file), encoding);
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((lineInfo = bufferedReader.readLine()) != null) {
                listStr.add(lineInfo);
            }
        } catch (IOException e) {
            log.info("io读取异常");
            log.info(e.getMessage());
        } finally {
            try {
                bufferedReader.close();
                inputStreamReader.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return listStr;
    }

    /**
     * @ClassName:ReadResourse
     * @Description:得到资源文件
     * @Author:Shi Xiaohao
     * @E-mail:sfturing@gmail.com
     * @Date :2017/12/3 上午11:13
     */
    public  File getTXTFile() {
        String inputPath;
        File file;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入sql文件路径。（仅限txt文件）");
            inputPath = sc.nextLine().trim();
            if (inputPath.endsWith(".txt")||inputPath.endsWith(".sql")) {
                file = new File(inputPath);
                if (file.isFile() || file.exists()) {
                    break;
                }
            }
            System.out.println("输入文件不是txt file 或者不存在");
            continue;
        }
        return file;
    }




}
