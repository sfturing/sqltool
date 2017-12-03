import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author:Shi Xiaohao
 * @e-mail:sfturing@gmail.com
 * @date :2017/12/3 上午11:22
 */
public class SplitResourse {


    /**
     * @ClassName:SplitResourse
     * @Description:消除文件中注释，之后遍历不需要考虑注释的问题.返回的是list<String>,每个是一行。
     * @Author:Shi Xiaohao
     * @E-mail:sfturing@gmail.com
     * @Date :2017/12/3 上午11:24
     * @param
     */
    public List<String> eliminateAnnotation(List<String> resourseList) {
        boolean quoteMark = false;
        boolean annotationMark = false;
        StringBuilder strTemp = new StringBuilder();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < resourseList.size(); i++) {
            char[] sqlChar = resourseList.get(i).toCharArray();
            for (int j = 0; j < sqlChar.length; j++) {
                if (sqlChar[j] == '-' && sqlChar.length > (j + 1) && sqlChar[j + 1] == '-' && quoteMark == false) {
                    break;
                }
                if (sqlChar[j] == '/' && sqlChar.length > (j + 1) && sqlChar[j + 1] == '*' && quoteMark == false) {
                    annotationMark = true;
                    j++;
                    continue;
                }
                if (annotationMark == true && sqlChar[j] == '*' && sqlChar.length > (j + 1) && sqlChar[j + 1] == '/') {
                    annotationMark = false;
                    j++;
                    continue;
                }
                if (sqlChar[j] == '\'' && annotationMark == false) {
                    quoteMark = !quoteMark;
                }
                if (annotationMark == false) {
                    strTemp.append(sqlChar[j]);
                }
            }
            if (!strTemp.toString().trim().equals("")){
                list.add(strTemp.toString());
                strTemp.setLength(0);
            }
        }
        return list;
    }


    /**
     * @ClassName:SplitResourse
     * @Description:根据读入资源，分出不同的SQL语句，进行不同的分割。
     * @Author:Shi Xiaohao
     * @E-mail:sfturing@gmail.com
     * @Date :2017/12/3 下午5:07
     */

    public List<String> getSqls(List<String> inputList){
        List<String> sqls = new ArrayList<String>();
        SqlInfo sqlInfo = new SqlInfo(inputList,0,0);
        while (sqlInfo.getListStart() + 1< sqlInfo.getListStr().size()){
            sqlInfo = SplitCommonSql(sqlInfo);
            sqls.add(sqlInfo.getOutSql());
            sqlInfo.setOutSql("");
        }
        return sqls;
    }


    /**
     * @ClassName:SplitResourse
     * @Description:判断sql。
     * @Author:Shi Xiaohao
     * @E-mail:sfturing@gmail.com
     * @Date :2017/12/3 下午5:23
     */
    public int judgeSql(SqlInfo sqlInfo){


        return 0;
    }


    /**
     * @ClassName:SplitResourse
     * @Description:分解普通SQL和其他SQL.
     * @Author:Shi Xiaohao
     * @E-mail:sfturing@gmail.com
     * @Date :2017/12/3 下午10:56
     */
    public SqlInfo SplitCommonSql(SqlInfo sqlInfo){
        //设置引号标记
        boolean quoteMark = false;
        StringBuilder stringBuilder = new StringBuilder();
        String outStr = null;
        List<String> listStr = sqlInfo.getListStr();
        int listStart = sqlInfo.getListStart();
        int charStart = sqlInfo.getCharStart();
        for (int i = listStart; i <listStr.size() ; i++) {
            char[] sqlChar = listStr.get(i).toCharArray();
            for (int j = charStart; j < sqlChar.length; j++) {
                stringBuilder.append(sqlChar[j]);
                if (sqlChar[j] == '\'') {
                    quoteMark = !quoteMark;
                }
                if (sqlChar[j] == ';' && quoteMark == false ) {
                    outStr = stringBuilder.toString();
                    sqlInfo.setOutSql(outStr);
                    sqlInfo.setListStart(i);
                    sqlInfo.setCharStart(j + 1);
                    break;
                }
            }
            charStart = 0;
            if (!("".equals(outStr) || outStr == null)){
                break;
            }
        }
        return sqlInfo;
    }

    /**
     * @ClassName:SplitResourse
     * @Description:
     * @Author:Shi Xiaohao
     * @E-mail:sfturing@gmail.com
     * @Date :2017/12/3 下午10:56
     */
    public SqlInfo SplitPLSql(SqlInfo sqlInfo){
        //设置引号标记
        boolean quoteMark = false;
        StringBuilder stringBuilder = new StringBuilder();
        String outStr = null;
        List<String> listStr = sqlInfo.getListStr();
        int listStart = sqlInfo.getListStart();
        int charStart = sqlInfo.getCharStart();
        for (int i = listStart; i <listStr.size() ; i++) {
            char[] sqlChar = listStr.get(i).toCharArray();
            for (int j = charStart; j < sqlChar.length; j++) {
                stringBuilder.append(sqlChar[j]);
                if (sqlChar[j] == '\'') {
                    quoteMark = !quoteMark;
                }
            }
            charStart = 0;
            if (!("".equals(outStr) || outStr == null)){
                break;
            }
        }
        return sqlInfo;
    }
}
