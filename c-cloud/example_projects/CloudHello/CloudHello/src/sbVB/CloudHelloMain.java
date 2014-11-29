/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbVB;

/**
 *
 * @author sbvb
 */
public class CloudHelloMain {
    public String hello() {
        return "Cloud Hello UFRJ";
    }

    public String plus_a_b(String in_a, String in_b) {
        return in_a+"a, "+in_b+"b";
    }

    public String [] plus_a_b2(String in_a, String in_b) {
        String ret[] = new String [2];
        ret[0] = in_a+"a";
        ret[1] = in_b+"b";        
        return ret;
    }
}
