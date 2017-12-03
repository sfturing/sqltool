import java.util.List;

/**
 * @description:
 * @author:Shi Xiaohao
 * @e-mail:sfturing@gmail.com
 * @date :2017/12/3 下午1:59
 */
public class Execute {


    public static void main(String[] args) {
        ReadResourse readResourse = new ReadResourse();
        SplitResourse splitResourse = new SplitResourse();
        List<String> listStr = readResourse.readFile();
        List<String> list = splitResourse.eliminateAnnotation(listStr);
        List<String> out = splitResourse.getSqls(list);
        for (int i = 0; i < out.size(); i++) {
            System.out.println(out.get(i));
        }

    }
}
