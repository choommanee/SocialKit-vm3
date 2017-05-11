package co.aquario.socialkit.model;

import com.google.gson.annotations.Expose;

import java.lang.reflect.Field;


public class UserProfile {
    @Expose
    public String id;
    @Expose
    public String name;
    //@SerializedName("avatar_url")
    @Expose
    public String avatar;
    //@SerializedName("cover_url")
    @Expose
    public String cover;
    @Expose
    public String username;
    @Expose
    public String email;

    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
}
