package by.bsuir.tritpo.converter;

import java.util.ArrayList;
import java.util.List;

public class NumberConverter {

    public List<String> convert(String number, int radix) {
        List<String> result = new ArrayList<>();
        result.add(Integer.toBinaryString(Integer.parseInt(number, radix)));
        result.add(Integer.toOctalString(Integer.parseInt(number, radix)));
        result.add(Integer.toString(Integer.parseInt(number, radix)));
        result.add(Integer.toHexString(Integer.parseInt(number, radix)));
        return result;
    }
}
