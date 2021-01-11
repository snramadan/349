import java.lang.String;
import java.util.ArrayList;

class CombObjects
{
   public ArrayList<String> getLexPerm(String str)
   {
      ArrayList<String> list = new ArrayList<String>();
      int i;

      if(str.length() == 0) 
      {
         list.add(str);
         return list;
      }
      for(i = 0; i < str.length(); i++) 
      {
         String m = str.substring(0, i) + str.substring(i + 1);
         ArrayList<String> p = getLexPerm(m);
         
         for(String st : p) 
         {
            list.add(str.charAt(i) + st);
         }
      }
      return list;

   }

   public ArrayList<String> getMinChgPerm(String str)
   {
      ArrayList<String> list = new ArrayList<String>();
      int i;
      String l, r;

      if(str.length() == 0) 
      {
         list.add(str);
         return list;
      }

      String s = "" + str.charAt(str.length() - 1);
      String p = str.substring(0, str.length() - 1);
      ArrayList<String> ps = getMinChgPerm(p);

      boolean temp = true;

      for(String st : ps) {

         if (temp) 
         {
            for(i = st.length(); i >= 0; i--) 
            {
               l = st.substring(0, i);
               r = st.substring(i, st.length());
               list.add(l + s + r);
            }

            temp = false;
         }
         else 
         {
            for(i = 0; i <= st.length(); ++i) {

               l = st.substring(0, i);
               r = st.substring(i, st.length());
               list.add(l + s + r);
            }

            temp = true;
         }
      }
      return list;
   }
}
