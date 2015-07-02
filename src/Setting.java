
/**
 * Created by Canon on 2015-06-25.
 */
public class Setting {
    private static Tool currentTool = Tool.Select;
    private static int layer = 0;

    private static boolean isShift=false;

    private Setting() {

    }

    public static int getLayer() {
        layer += 1;
        System.out.println("Current Layer: "+ layer);
        return layer;

    }

    public static Tool getCurrentTool() {
        return currentTool;
    }

    public static void setCurrentTool(Tool tool) {
        currentTool = tool;
    }

    public static void setShift(boolean shift){
        isShift=shift;
    }

    public static boolean getShiftStatus(){
        return isShift;
    }
}
