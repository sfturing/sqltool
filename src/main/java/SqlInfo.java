import java.util.List;

/**
 * @description:
 * @author:Shi Xiaohao
 * @e-mail:sfturing@gmail.com
 * @date :2017/12/3 下午4:45
 */
public class SqlInfo {
    private List<String> listStr;
    private int listStart;
    private int charStart;
    private String outSql;
    public SqlInfo(){

    }
    public SqlInfo(List<String> listStr,int listStart,int charStart){
        this.listStr = listStr;
        this.listStart = listStart;
        this.charStart = charStart;
    }

    public List<String> getListStr() {
        return listStr;
    }

    public void setListStr(List<String> listStr) {
        this.listStr = listStr;
    }

    public int getListStart() {
        return listStart;
    }

    public void setListStart(int listStart) {
        this.listStart = listStart;
    }


    public int getCharStart() {
        return charStart;
    }

    public void setCharStart(int charStart) {
        this.charStart = charStart;
    }


    public String getOutSql() {
        return outSql;
    }

    public void setOutSql(String outSql) {
        this.outSql = outSql;
    }
}
