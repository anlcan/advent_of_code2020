package com.advent2020.two;

import com.advent.two.Program;
import com.advent2020.Util;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created on 08.12.19.
 */
public class ProgramTest {

    @Test
    public void simpleCaseOne() {
        var input = "1,0,0,0,99";
        var p1 = Program.of(input);
        var input1 = p1.execute();
        assertEquals(2, input1);
    }
    @Test
    public void simpleCaseTwo(){

        var p2  = Program.of("2,3,0,3,99");
        var input2 = p2.execute();
        assertEquals(2, input2);
    }

    @Test
    public void simpleCaseThree(){

        var p  = Program.of("1,1,1,4,99,5,6,0,99");
        var input = p.execute();
        assertEquals(30, input);
    }

    @Test
    public void inputOne(){
        List<String>  input1 = Util.readStrings("/two/input1.txt");
        Program p = Program.of(input1.get(0));

        assertEquals(2782414, p.execute());
    }

    @Test
    public void inputTwo() {
        String input = Util.readStrings("/two/input1.txt").get(0);


        for (int noun : IntStream.rangeClosed(0, 99).toArray()){
            for (int verb : IntStream.rangeClosed(0, 99).toArray()){
                var p =  Program.of(input);
                p.setNounAndVerb(noun, verb);
                int result = p.execute();
                if (2782414 == result){
                    //System.out.println(100*noun+verb);
                    assertEquals(1202, 100*noun+verb);
                    //break;
                }

                if ( 19690720 == result){
                    System.out.println(100*noun+verb);
                }

            }
        }

    }
}
