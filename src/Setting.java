
/**
 * Created by Canon on 2015-06-25.
 */
public class Setting {
    private static Setting instance = new Setting();
    private Tool currentTool = Tool.Select;
    private static int layer = 0;

    private Setting() {

    }

    public static int getLayer() {
        layer += 1;
        System.out.println("Current Layer: "+ layer);
        return layer;

    }

    public static Setting getInstance() {
        return instance;
    }

    public Tool getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }
}
