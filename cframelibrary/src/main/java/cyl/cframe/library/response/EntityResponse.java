
package cyl.cframe.library.response;

/**
 * Json对象
 * 
 * @author Richard.Ma
 */
public class EntityResponse<T> extends Response {

    private static final long serialVersionUID = -7203786896447223056L;

    private T                 object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
