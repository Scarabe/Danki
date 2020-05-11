import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String [ ] args)    {


        String batata = "isabella 123.12312 !@#$%¨&*";

        Integer testeInteger = 123;
        Float teste = 12313223123.123123123123f;
        Double testeDouble = 1212312323.12312312312;
        BigDecimal testeBigDecimal = new BigDecimal("999999.999999");


        String[] extrato = new String[3];
        extrato[0] = "+1.50";
        extrato[1] = "-3.50";
        extrato[2] = "+50";

        System.out.println(extrato[2]);

        List<String> lista1 = new ArrayList<String>();
        lista1.add("+1.50");
        lista1.add("-3.50");
        lista1.add("+50");

        List<String> lista2 = new ArrayList<String>();
        lista2.add("+100.50");
        lista2.add("-35.50");

        List<String> lista3 = new ArrayList<String>();
        lista3.add("-150");

        List<List<String>> extratoLista = new ArrayList<>();

        extratoLista.add(lista1);
        extratoLista.add(lista2);
        extratoLista.add(lista3);


        System.out.println(extratoLista);
    }
}
