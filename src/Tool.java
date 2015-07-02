/**
 * Created by Canon on 2015-06-24.
 */
public enum Tool {
    Select("Select"), Rectangle("Rectangle"), Pencil("Pencil"), Line("Line"), Oval("Oval"), MultiSelect("MultiSelect"),
    Group("Group"), Ungroup("Ungroup") ;

    public String getName() {
        return name;
    }

    private String name;

    Tool(String value){
        name=value;
    }

    public static Tool getEnumFromValue(String val){
        for (Tool enu:Tool.values()){
            if(val.equals(enu.getName())){
                return enu;
            }
        }
        return null;
    }

}
