public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res += n & 1;
            n >>>= 1;         
        }
        return res;
    }
}

int reverseBits(int n) {
  return Integer.reverse(n);
}
public class Solution {
    // you need treat n as an unsigned value
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public int reverseBits(int n) {
        int res = 0;
        for(int i = 0; i < 4; i++){
            int tmp = n & 0xFF;
            if(map.containsKey(tmp)){
                res = (res << 8) + map.get(tmp); 
            } else{
                res = (res << 8) + reverse8Bits(tmp);
            }
            n >>= 8;
        }
        
        return res;
    }
    
    private int reverse8Bits(int n){
        int res = 0;
        int tmp = n;
        for(int i = 0; i < 8; i++){
            res = (res << 1) + (tmp & 1);
            tmp >>= 1;
        }
        map.put(n, res);
        return res;
    }
}
