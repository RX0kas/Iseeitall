public class TestString {
    static String getEntityName(String entity) {
        String[] name = entity.split("\\.");
        return name[name.length-1].toUpperCase();
    }
    public static void main(String[] args) {System.out.println(getEntityName("entity.minecraft.pig"));}

}