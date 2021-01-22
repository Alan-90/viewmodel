package www.hbj.cloud.baselibrary.ngr_library.component.dbentity;

/**
 * 所有表结构类都必须继承自该类，以免被混淆
 * Created by zhengji on 2016/11/24.
 */
public abstract class EntityBase {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
