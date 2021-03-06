// 406. Queue Reconstruction by Height
public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> list = new LinkedList<>();
        for (int[] p : people) {
            list.add(p[1], p);
        }
        return list.toArray(new int[list.size()][]);
    }
}

// 179. Largest Number
class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        String[] digits = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            digits[i] = String.valueOf(nums[i]);
            // digits[i] = Integer.toString(nums[i]);
        }
        // Comparator<String> comp = new Comparator<String>() {
        //     public int compare(String str1, String str2) {
        //         String s1 = str1 + str2;
        //         String s2 = str2 + str1;
        //         return s2.compareTo(s1);
        //     }
        // };
        // Arrays.sort(digits, comp);
        Arrays.sort(digits, (a, b) -> (b + a).compareTo(a + b));
        if (digits[0].charAt(0) == '0') {
            return "0";
        }
        String res = "";
        for (int i = 0; i < digits.length; i++) {
            res += digits[i];
        }
        return res;
    }
}

// 252. Meeting Rooms

class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end) {
                return false;
            }
        }
        return true;
    }
}
