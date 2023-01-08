import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.hua.hash.hash_exercise_1.Dictionary;
import org.hua.hash.hash_exercise_1.OpenAdressingHashTable;
import org.junit.experimental.theories.suppliers.TestedOn;

public class Test {

    public static final int SIZE = 10000;
    /**
     * 
     */
    @org.junit.Test
    public void testTest(){
            Dictionary <Integer , Integer> dict = new OpenAdressingHashTable<>();

            ArrayList<Integer> values = new ArrayList<>();
            Random rand = new Random(17);

            for(int i=0; i<SIZE; i++){
                int m = rand.nextInt(1000);
                values.add(m);
                dict.put(m, m+1);
            }

            for(Integer v: values){
                assertTrue(dict.get(v) == v+1);
            }
    }
    
    @org.junit.Test
    public boolean testTest2(){
        Dictionary <Integer , Integer> dict = new OpenAdressingHashTable<>();

        ArrayList<Integer> values = new ArrayList<>();
        Random rand = new Random(17);
        for(int i=0; i<SIZE; i++){
            int m = rand.nextInt(1000);
            values.add(m);
            dict.put(m, m+1);
        }
        for(int i=0; i<SIZE-1; i++){
            int m = rand.nextInt(1000);
            values.add(m);
            dict.remove(m);
        }
        if(dict.size() == 1 ){
            return true;
        }
        else {
        return false;
        }
        
    }
    @org.junit.Test
    public boolean  testTest3(){
        Dictionary <Integer , Integer> dict = new OpenAdressingHashTable<>();

        ArrayList<Integer> values = new ArrayList<>();
        
        for(int i=0; i<10; i++){
            int m = i;
            values.add(m);
            dict.put(m, m+1);
        }
        boolean result=false;
        for(int i=0;i<=dict.size();i++){
            if(dict.contains(2)){
                result= true;
            }
        }
        return result;
        }
    }

