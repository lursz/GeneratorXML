package org.generator;

import org.generator.entities.JPK;
import org.generator.entities.TAdresPolski1;
import org.generator.entities.TIdentyfikatorOsobyNiefizycznej1;
import org.generator.entities.TNaglowek;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WrapperJPKTest {
    @Test
    void testHeader() {
        // create an instance of WrapperJPK
        WrapperJPK WrapperJPK = new WrapperJPK();

        WrapperJPK.Header();

        JPK jpk = WrapperJPK.getJpk();

        JPK.Naglowek naglowek = jpk.getNaglowek();

        TNaglowek.KodFormularza kodFormularza = naglowek.getKodFormularza();
        assertEquals("JPK_FA", kodFormularza.getValue().value());
        assertEquals("JPK_FA (3)", kodFormularza.getKodSystemowy());
        assertEquals("1-0", kodFormularza.getWersjaSchemy());
        assertEquals((byte) 3, naglowek.getWariantFormularza());
        assertEquals((byte) 1, naglowek.getCelZlozenia());
        assertEquals("1208", naglowek.getKodUrzedu());
    }


    @Test
    void testSubject() {
        // create an instance of MyClass
        WrapperJPK myClass = new WrapperJPK();

        // Invoke the function to be tested
        myClass.Subject();

        // Get the jpk object
        JPK jpk = myClass.getJpk();

        // Get the Podmiot1 object
        JPK.Podmiot1 podmiot1 = jpk.getPodmiot1();

        // Check the value of IdentyfikatorPodmiotu
        TIdentyfikatorOsobyNiefizycznej1 id = (TIdentyfikatorOsobyNiefizycznej1) podmiot1.getIdentyfikatorPodmiotu();
        assertEquals("6762484560", id.getNIP());
        assertEquals("\"CORE LOGIC\" SPÓŁKA Z OGRANICZONĄ ODPOWIEDZIALNOŚCIĄ", id.getPelnaNazwa());

        // check the value of AdresPodmiotu
        TAdresPolski1 adresPodmiotu = (TAdresPolski1) podmiot1.getAdresPodmiotu();
        assertEquals("PL", adresPodmiotu.getKodKraju().value());
        assertEquals("małopolskie", adresPodmiotu.getWojewodztwo());
        assertEquals("m. Kraków", adresPodmiotu.getPowiat());
        assertEquals("ul. Feliksa Radwańskiego", adresPodmiotu.getUlica());
        assertEquals("15", adresPodmiotu.getNrDomu());
        assertEquals("1", adresPodmiotu.getNrLokalu());
        assertEquals("Kraków", adresPodmiotu.getMiejscowosc());
        assertEquals("30-065", adresPodmiotu.getKodPocztowy());
    }
}







