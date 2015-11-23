package me.daoke.driving.testrule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: chenlong
 * Date: 2015/5/11
 * Time: 13:40
 */
public class HashAlgorithms {


     /**//**
     * 加法hash
     * @param key 字符串
     * @param prime 一个质数
     * @return hash结果
     */
    public static int additiveHash(String key, int prime)
    {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); i++)
            hash += key.charAt(i);
        return (hash % prime);
    }

    /**//**
     * 旋转hash
     * @param key 输入字符串
     * @param prime 质数
     * @return hash值
     */
    public static int rotatingHash(String key, int prime)
    {
        int hash, i;
        for (hash=key.length(), i=0; i<key.length(); ++i)
            hash = (hash<<4)^(hash>>28)^key.charAt(i);
        return (hash % prime);
        //   return (hash ^ (hash>>10) ^ (hash>>20));
    }


    public static void main(String args[]){
         List<String> list = new ArrayList<String>();
        list.add("0001sOupN5");
        list.add("0002U2xSl3");
        list.add("0002UHMiHF");
        list.add("0003nNflaB");
        list.add("0004Ml9aHw");
        list.add("0004tlCYZT");
        list.add("0007ltl0ll");
        list.add("0008Q9uQSy");
        list.add("00096DrUSv");
        list.add("0009lElMpB");
        list.add("000Bdlrind");
        list.add("000Bqepk2Y");
        list.add("000CSzcsMG");
        list.add("000CkEzkEv");
        list.add("000CySmqYl");
        list.add("000DlrL2AS");
        list.add("000EbnWlMF");
        list.add("000GlobaoJ");
        list.add("000HHMtp8w");
        list.add("000II7sTwz");
        list.add("000JluUiil");
        list.add("000Jpvvv0r");
        list.add("000LwsHtyt");
        list.add("000MKyI1SB");
        list.add("000NlMqrbl");
        list.add("000NsYepry");
        list.add("000PnZZ8yG");
        list.add("000Q8qmrDQ");
        list.add("000QJk9tJy");
        list.add("000Qlq6rTo");
        list.add("000Qow9wbs");
        list.add("000V3lmgXs");
        list.add("000W9zlGtA");
        list.add("000XACEuTk");
        list.add("000XRl93Fp");
        list.add("000Xv6nKcg");
        list.add("000bek4csu");
        list.add("000buLu9rs");
        list.add("000e2KE0kp");
        list.add("000g2FcVQ5");
        list.add("000gysJrHR");
        list.add("000iCXAXFM");
        list.add("000jIcqvSz");
        list.add("000kDakVRm");
        list.add("000kmjwffZ");
        list.add("000l9dv2Ml");
        list.add("000lAlNNf9");
        list.add("000lM7fqMs");
        list.add("000lSymQlY");
        list.add("000lTlpJuh");
        list.add("000lTvoPPm");
        list.add("000lbJmv1l");
        list.add("000lhRBlAe");
        list.add("000mKPhqfi");
        list.add("000olMUPhp");
        list.add("000pEldMGp");
        list.add("000pqMqqJw");
        list.add("000qAtLsNl");
        list.add("000rIlPEle");
        list.add("000rnTkXkl");
        list.add("000sW4Zpk2");
        list.add("000sWjgmxl");
        list.add("000tZll0L9");
        list.add("000ulklq8H");
        list.add("000v34UmkV");
        list.add("000vUAhYGg");
        list.add("000vlwmt9t");
        list.add("000wDMlNGI");
        list.add("000wuhAjU3");
        list.add("000wunikZm");
        list.add("000xjIuWmp");
        list.add("000yFwWecl");
        list.add("000yJlsvlG");
        list.add("000yVbQlcl");
        list.add("000ylNFmVl");
        list.add("000yrdTlK7");
        list.add("000zotwvw0");
        list.add("00100OVUfR");
        list.add("0010i52mgH");
        list.add("00126VmGzX");
        list.add("00166Ylsk5");
        list.add("001843zEbz");
        list.add("0019NJpJ0e");
        list.add("0019av0JvE");
        list.add("001ACmtmgl");
        list.add("001AJkCelr");
        list.add("001AN39nLh");
        list.add("001Eso8nZl");
        list.add("001FSujHM7");
        list.add("001JGkAkOL");
        list.add("001JlsYa8Y");
        list.add("001Kgj5VEu");
        list.add("001M8KkIi4");
        list.add("001NLqk7mD");
        list.add("001NslHdeo");
        list.add("001NvBnlVn");
        list.add("001QwmMzx1");
        list.add("001UuZ3mMI");
        list.add("001Yg54ylv");
        list.add("001ZZJIllA");
        list.add("001aRNDOA4");
        list.add("001bVCbDml");
        list.add("001cl1M28C");
        list.add("001grsm4Ul");
        list.add("001gwllSPJ");
        list.add("001l4z8vOT");
        list.add("001lHHmx9J");
        list.add("001lLnOt3v");
        list.add("001lkCeQXl");
        list.add("001lklpq8k");
        list.add("001llikZdQ");
        list.add("001loGLVkr");
        list.add("001mBZIj8n");
        list.add("001mQysqKn");
        list.add("001nUkZRlF");
        list.add("001nkFxmlL");
        list.add("001nklz1G3");
        list.add("001o1a5Afw");
        list.add("001p3luM7V");
        list.add("001rPV1kl5");
        list.add("001rUYu4cS");
        list.add("001raWtSuN");
        list.add("001s66H0We");
        list.add("001sZW9iYl");
        list.add("001sa2xlaD");
        list.add("001tBlfum1");
        list.add("001wcPOllo");
        list.add("001wq1Jlwc");
        list.add("001yNiofJQ");
        list.add("001zlePqil");
        list.add("001zzcnV4O");
        list.add("00218d8lUV");
        list.add("0021lplsxE");
        list.add("0021tmKY4w");
        list.add("0022lku5lk");
        list.add("0022purlSU");
        list.add("0023aCn3kJ");
        list.add("0023c3f5Cl");
        list.add("0023lIsdOn");
        list.add("0023ysLumw");
        list.add("0024S3yD6m");
        list.add("0024cqt0lg");
        list.add("0024suCsCM");
        list.add("0026p0C4ll");
        list.add("0028ANVHZ8");
        list.add("0028mAdJfT");
        list.add("002Anp3afm");
        list.add("002CBlI4tq");
        list.add("002DJllBxm");
        list.add("002DLREIV6");
        list.add("002Dmll0rr");
        list.add("002EfcP6P3");
        list.add("002HPoHrim");
        list.add("002He4m3xp");
        list.add("002Hilhlsa");
        list.add("002HvKshI4");
        list.add("002JullvFE");
        list.add("002JwmIVv8");
        list.add("002KuxsMCf");
        list.add("002MillpVl");
        list.add("002NeBlvf8");
        list.add("002Rx8wGWn");
        list.add("002UfS1wU1");
        list.add("002VEXlfmQ");
        list.add("002Xl2asiH");
        list.add("002bwzmq5l");
        list.add("002cYPP4yC");
        list.add("002cwbFMMG");
        list.add("002fmJtNxj");
        list.add("002gb4y4ll");
        list.add("002gq2prsm");
        list.add("002hAgnS1J");
        list.add("002hlRa0bu");
        list.add("002islAKGh");
        list.add("002jklrswH");
        list.add("002klhr4yQ");
        list.add("002kvsEllP");
        list.add("002l9mQUkw");
        list.add("002lXwlORl");
        list.add("002lgbpm9k");
        list.add("002lklBlow");
        list.add("002llbmiKu");
        list.add("002lnykgM8");
        list.add("002lt66lwl");
        list.add("002lzynYZV");
        list.add("002mUll1Kl");
        list.add("002mZwllcy");
        list.add("002mbFlHYR");
        list.add("002mfKmo4q");
        list.add("002o6mNkEl");
        list.add("002pdve2w4");
        list.add("002pljIVFc");
        list.add("002pt0iLEt");
        list.add("002pwll7Rs");
        list.add("002qn50IlV");
        list.add("002s8W6E6u");
        list.add("002sCwlkil");
        list.add("002spA2CVl");
        list.add("002tlOZums");
        list.add("002trgoRSV");
        list.add("002txPwPlb");
        list.add("002u2eX0w0");
        list.add("002uS8nknS");
        list.add("002un0JsUy");
        list.add("002unWl6J4");
        list.add("002vUPxyEy");
        list.add("002vZlysFu");
        list.add("002w7kobtk");
        list.add("002x4lyl13");
        list.add("002y0Alg1k");
        list.add("002yp400u1");
        list.add("00305i2iUu");
        list.add("0030muMrll");
        list.add("0030tnxjMr");
        list.add("0032Inlllt");
        list.add("0032piaT3q");
        list.add("0037xvNmlC");
        list.add("003C99XlQH");
        list.add("003CS2j94w");
        list.add("003CsqHyN3");
        list.add("003D3u4Vkt");
        list.add("003DfnkXlU");
        list.add("003EDwSzRY");
        list.add("003EqUmOLd");
        list.add("003Hbyllmi");
        list.add("003I03lIHq");
        list.add("003IemYZ9l");
        list.add("003K7tex3C");
        list.add("003MMjHsGa");
        list.add("003MjqZ1Pl");
        list.add("003Mlewmls");
        list.add("003Mxllkey");
        list.add("003NOQ0YV0");
        list.add("003OGyytxL");
        list.add("003RJlUNku");
        list.add("003RblXcXl");
        list.add("003T0tCjWl");
        list.add("003T6l0Num");
        list.add("003TasbD2s");
        list.add("003XXRWiot");
        list.add("003XfkUg0W");
        list.add("003dVaQqkm");
        list.add("003eKe4Alr");
        list.add("003edCEtxq");
        list.add("003fI7eQn4");
        list.add("003hlERprO");
        list.add("003hml4yem");
        list.add("003iecnAo1");
        list.add("003koFkh2Y");
        list.add("003l9JkYpK");
        list.add("003lQ2u7bd");
        list.add("003lfercci");
        list.add("003lzClll3");
        list.add("003mkllj4u");
        list.add("003mtl2lvr");
        list.add("003mvtQZlz");
        list.add("003myH2tXG");
        list.add("003mz63wkY");
        list.add("003nwqtSg4");
        list.add("003nzSZW1k");
        list.add("003o03sVkU");
        list.add("003oaIqulW");
        list.add("003p2SkKgl");
        list.add("003pQrKx2l");
        list.add("003pfU6lbe");
        list.add("003ue3SRlU");
        list.add("003ukt7t0i");
        list.add("003v2NRj5k");
        list.add("003v9fs6Ek");
        list.add("003vAlsSSE");
        list.add("003vOfikOl");
        list.add("003wOJZwGf");
        list.add("003xioQG1h");
        list.add("003yCNxgMw");
        list.add("003zlDwqPC");
        list.add("003zrMEsb7");
        list.add("0040jjwhi4");
        list.add("0040vkQcal");
        list.add("0044McSwop");
        list.add("0044lqAadM");
        list.add("0044tp7Xxm");
        list.add("0045ulVCJA");
        list.add("0048AQnlI0");
        list.add("0048PtglZf");
        list.add("0048Zgm09Q");
        list.add("0048mkL0El");
        list.add("004ANlQiqo");
        list.add("004COgkllO");
        list.add("004D6Xlm7z");
        list.add("004D834twr");
        list.add("004EqcRlsc");
        list.add("004G0MNWmk");
        list.add("004Gbsl48U");
        list.add("004Iga0a0l");
        list.add("004J3llPS6");
        list.add("004KEBUlxL");
        list.add("004M5rYlKE");
        list.add("004NVNwYXx");
        list.add("004Njtm009");
        list.add("004OvvvLwD");
        list.add("004QKZmOkQ");
        list.add("004R7olsAp");
        list.add("004SGlTu1h");
        list.add("004SMYlS6D");
        list.add("004WYmAqYj");
        list.add("004WmckW4K");
        list.add("004Xaz2OPG");
        list.add("004XcvMlL0");
        list.add("004YlXufkE");
        list.add("004Zrqhesm");
        list.add("004bk8jSDD");
        list.add("004dhbSwIQ");
        list.add("004dzLlqll");
        list.add("004elG3GHk");
        list.add("004emlzlyS");
        list.add("004eu2aTvm");
        list.add("004flntMw9");
        list.add("004hikRlzi");
        list.add("004hrTaywf");
        list.add("004iiJWU6C");
        list.add("004kZMoXl4");
        list.add("004klUNXm4");
        list.add("004kzrZoFB");
        list.add("004l4Dftso");
        list.add("004l8ccYkO");
        list.add("004lME0smU");
        list.add("004lNUld2Q");
        list.add("004lVCAcVF");
        list.add("004lVMlXRf");
        list.add("004lWXlnXA");
        list.add("004lY4hE2Q");
        list.add("004lekEFFl");
        list.add("004lg0lkln");
        list.add("004llashku");
        list.add("004llmA1ll");
        list.add("004lm4mC1i");
        list.add("004lmAoTbl");
        list.add("004lwlCFbk");
        list.add("004mCXmclV");
        list.add("004mScm0kR");
        list.add("004melArIs");
        list.add("004mjkfel7");
        list.add("004mktmmwT");
        list.add("004mlaHkai");
        list.add("004mtlT2Mt");
        list.add("004nkF8JM0");
        list.add("004oPr9qRt");
        list.add("004pYKlCmU");
        list.add("004psd24El");
        list.add("004sEd6MbC");
        list.add("004sLyz1ov");
        list.add("004t3xRT8x");
        list.add("004tEmUpVO");
        list.add("004tOQa0IJ");
        list.add("004tWxl2A0");
        list.add("004uYJmNCq");
        list.add("004ul7E9Sm");
        list.add("004uyFGtR0");
        list.add("004vNiZ0CT");
        list.add("004wemCslt");
        list.add("004wmjElwS");
        list.add("004wrEspl4");
        list.add("004wxlrypO");
        list.add("004wxxCtWb");
        list.add("004yD3Emxs");
        list.add("00502ethBl");
        list.add("0051t86TWD");
        list.add("0052WCJqlZ");
        list.add("0055JttyDa");
        list.add("005AYlxAiU");
        list.add("005FtPl1hg");
        list.add("005HdOsTwq");
        list.add("005M0QowJw");
        list.add("005N1ma0zS");
        list.add("005dOGRmm1");
        list.add("005eu0YL5D");
        list.add("005fJk2d4k");
        list.add("005hQdQlHQ");
        list.add("005il60rcw");
        list.add("005kkflell");
        list.add("005klemsKP");
        list.add("005lYt5nlt");
        list.add("005lj5sItT");
        list.add("005lluY9l1");
        list.add("005lp1AMZS");
        list.add("005md0Plpc");
        list.add("005mymYuUb");
        list.add("005nh84OPJ");
        list.add("005sh6OsB9");
        list.add("005sosRzn3");
        list.add("005uolLlcU");
        list.add("005x3kOFiq");
        list.add("005yImCiwf");
        list.add("005zkmkFpl");
        list.add("005zslmKNe");
        list.add("0060GjHlls");
        list.add("0060pgBTdk");
        list.add("0061rxmlCl");
        list.add("0064mUNDxl");
        list.add("0064ylAoqT");
        list.add("0067mllNEM");
        list.add("0067tJABlw");
        list.add("0068tKJhuc");
        list.add("006EAlfyKl");
        list.add("006FRxISWN");
        list.add("006GllQsUn");
        list.add("006HMqllik");
        list.add("006K2jLhlF");
        list.add("006KXll7R6");
        list.add("006KlHsvUH");
        list.add("006OaRuxlL");
        list.add("006QnsdlgS");
        list.add("006SfllEVl");
        list.add("006Tckhils");
        list.add("006UpY476k");
        list.add("006WEgqk6e");
        list.add("006cBkIjlY");
        list.add("006fcqwIBX");
        list.add("006gWlrMYy");
        list.add("006j8lyJla");
        list.add("006jLw0lik");
        list.add("006jlQAlzl");
        list.add("006k0vl1KC");
        list.add("006kQCIltk");
        list.add("006kgwsM1T");
        list.add("006loOWOll");
        list.add("006mmVQlAl");
        list.add("006oCFpZZI");
        list.add("006olmBMTo");
        list.add("006qCSlbIc");
        list.add("006qIQmmlf");
        list.add("006qL3Q1M3");
        list.add("006qQSmsv0");
        list.add("006qlMLC8T");
        list.add("006qqlzAli");
        list.add("006rIllylb");
        list.add("006wfyiw3X");
        list.add("006xTlvrSk");
        list.add("006xdvmVfM");
        list.add("006yp00XnI");
        list.add("006zmDnmZj");
        list.add("0070lJUa4v");
        list.add("0074XtwNZp");
        list.add("0075gQEy7i");
        list.add("0075kulgKE");
        list.add("0077jeylFi");
        list.add("0077kLlucx");
        list.add("007AnGSN7M");
        list.add("007C8IOHlJ");
        list.add("007CaoveoS");
        list.add("007Eqopklv");
        list.add("007HZtByxm");
        list.add("007Il8QKKV");
        list.add("007Jwv8Pst");
        list.add("007L1uM4PC");
        list.add("007M6ZApyn");
        list.add("007RkwkvlQ");
        list.add("007S8lEmVW");
        list.add("007SLjydYP");
        list.add("007SlL7lkl");
        list.add("007Tal7Zll");
        list.add("007Uq1SXmI");
        list.add("007Uylq6No");
        list.add("007aQGlpmu");
        list.add("007cmnlK6t");
        list.add("007eFFlVSl");
        list.add("007hehNNdx");
        list.add("007k4OmlIj");
        list.add("007k8m8JDH");
        list.add("007l1NQylA");
        list.add("007l4mqlls");
        list.add("007lAQB2uz");
        list.add("007lHmniap");
        list.add("007lJluvAw");
        list.add("007lVhlaln");
        list.add("007lobqa4F");
        list.add("007lqUvlTl");
        list.add("007ltNtKAt");
        list.add("007lvzICko");
        list.add("007lzullWi");
        list.add("007m7oc9ho");
        list.add("007moOll0f");
        list.add("007onlAT6E");
        list.add("007plzrchL");
        list.add("007qDO0Msl");
        list.add("007sAJiKTN");
        list.add("007slOlwMm");
        list.add("007tUslifi");
        list.add("007uvmblww");
        list.add("007xllubRm");
        list.add("007y26mJzl");
        list.add("007ykG3Smr");
        list.add("007yqlSmNM");
        list.add("0080dyvHnf");
        list.add("0080lTqiOl");
        list.add("0081l4lSaw");
        list.add("0081sGmmSl");
        list.add("0082rxmUck");
        list.add("0083EdbqAi");
        list.add("0087NgWKQp");
        list.add("0087ldQfyl");
        list.add("008960elfl");
        list.add("00896r9llt");
        list.add("008D1zXp5V");
        list.add("008Dk1bilp");
        list.add("008Dr9f1Eq");
        list.add("008EQlKlpO");
        list.add("008ESAnMuk");
        list.add("008Flm4ZKp");
        list.add("008IZm0E4F");
        list.add("008JQ3cztk");
        list.add("008LE9lzQW");
        list.add("008Ly1lijo");
        list.add("008M3npkfl");
        list.add("008PlxEsln");
        list.add("008Q0JJMUl");
        list.add("008SE9lPIE");
        list.add("008SkpLZcu");
        list.add("008Th8mzBl");
        list.add("008UXbl4Y2");
        list.add("008V7BDlll");
        list.add("008Xlbeawn");
        list.add("008Xp6kVsA");
        list.add("008ZLqiGEw");
        list.add("008cJffltD");
        list.add("008ceYlTCl");
        list.add("008e0t2Xsl");
        list.add("008fkNKtn4");
        list.add("008hull3S2");
        list.add("008jlyOq8m");
        list.add("008k24eulC");
        list.add("008kdeleUl");
        list.add("008kuYdR0l");
        list.add("008lasupGq");
        list.add("008lglt8pQ");
        list.add("008llQ5kXz");
        list.add("008lmMklFZ");
        list.add("008mlppNdf");
        list.add("008npdkUUr");
        list.add("008o6WlplV");
        list.add("008oJsA1q3");
        list.add("008oRK0Ylq");
        list.add("008otjl0cE");
        list.add("008rS8w5SM");
        list.add("008s32In0u");
        list.add("008sfUPc6H");
        list.add("008tobpmqr");
        list.add("008uT3lEy7");
        list.add("008zlUagCT");
        list.add("0090Ilxkpl");
        list.add("0092xE4ALg");
        list.add("0095apmM2l");
        list.add("0098wIlfil");
        list.add("009DqllaYp");
        list.add("009DyqjJyM");
        list.add("009ICtkeHf");
        list.add("009KKckwIl");
        list.add("009QqPmsBo");
        list.add("009SmtD6tf");
        list.add("009Ul1BJro");
        list.add("009Uwmm8tZ");
        list.add("009YbWdSvl");
        list.add("009Yjdelta");
        list.add("009Ywcl84B");
        list.add("009dnk6fci");
        list.add("009enp3gkf");
        list.add("009gdS1l35");
        list.add("009gsyIXNz");
        list.add("009hIaEpHo");
        list.add("009hnya0oq");
        list.add("009iz3kmds");
        list.add("009j9Cb0Ll");
        list.add("009jEDi4en");
        list.add("009jQmX8Lk");
        list.add("009k0vyWkd");
        list.add("009kWm5thl");
        list.add("009kaulull");
        list.add("009l7llN09");
        list.add("009lHtGDoI");
        list.add("009lJ0mlpK");
        list.add("009lPdFBym");
        list.add("009ldlJXl4");
        list.add("009lll2lul");
        list.add("009m0l4lsw");
        list.add("009m0t24tt");
        list.add("009mS8Ejlc");
        list.add("009mtQu2lx");
        list.add("009mvCoD0g");
        list.add("009pesJmlR");
        list.add("009qGQMklh");
        list.add("009qllnMSq");
        list.add("009shGmvJ1");
        list.add("009tESLL3i");
        list.add("009tXDsmqj");
        list.add("009uwtsqPL");
        list.add("009vrpmSdl");
        list.add("009zagQAka");
        list.add("009zkhWlgl");
        list.add("00A2GslllI");
        list.add("00A3lsFmlc");
        list.add("00A7l4hM9k");
        list.add("00A7poQqdg");
        list.add("00A7uhNSl1");
        list.add("00A8mYs0zU");
        list.add("00A8nEXpkT");
        list.add("00A9Umuxus");
        list.add("00ABgDUesE");
        list.add("00ABs0Vske");
        list.add("00ACn3siIU");
        list.add("00ACthzwjZ");
        list.add("00AIRYlnHk");
        list.add("00AIxn9FKN");
        list.add("00AKaJnaVl");
        list.add("00AMQ8eVmC");
        list.add("00AMejlrAm");
        list.add("00AMlRm29k");
        list.add("00APXAEqiW");
        list.add("00AQGhawsa");
        list.add("00AQThElIn");
        list.add("00AQx0iMGf");
        list.add("00AREcAbKE");
        list.add("00ASimwhJp");
        list.add("00ASlCMu99");
        list.add("00ASm9dIvl");
        list.add("00ATllfhcG");
        list.add("00ATwlPmZG");
        list.add("00AUlMmsbk");
        list.add("00AWJsAIbV");
        list.add("00AXNEwlsl");
        list.add("00AXlvALlF");
        list.add("00AeRMEtfV");
        list.add("00AfOluluO");
        list.add("00AklPmMyO");
        list.add("00AkmQPrTl");
        list.add("00Akrsl3mA");
        list.add("00AkukoetZ");
        list.add("00Al0H3Eot");
        list.add("00AlEwlTls");
        list.add("00AlNoOD2L");
        list.add("00AlUBu8kp");
        list.add("00AleqX8iq");
        list.add("00AlgXRxxS");
        list.add("00AlllVYrH");
        list.add("00Alls7tlr");
        list.add("00AlmDSDaK");
        list.add("00AlmxUrmm");
        list.add("00AlrUlSOu");
        list.add("00AlrvmEMB");
        list.add("00Am1Ysfm7");
        list.add("00AmQgr03S");
        list.add("00AmclOtew");
        list.add("00AmelM8yG");
        list.add("00Aml20ee3");
        list.add("00Amu4yoa4");
        list.add("00Anu0pyqM");
        list.add("00AollQmSy");
        list.add("00AoyX0zVm");
        list.add("00Aploimop");
        list.add("00Apq0a6wp");
        list.add("00Apull3lH");
        list.add("00Ary2l7Xd");
        list.add("00AsQladpo");
        list.add("00AsRuQAQH");
        list.add("00AsolqlfM");
        list.add("00AsqilS4Y");
        list.add("00Au0sMs4x");
        list.add("00AuSvL3oU");
        list.add("00AvKwKBal");
        list.add("00Avcv0Fzl");
        list.add("00AvlhscGm");
        list.add("00AvyOzTPh");
        list.add("00AwGdlqll");
        list.add("00AwlwwQRS");
        list.add("00AyuFzmlN");
        list.add("00AzmiAYgw");
        list.add("00AzyTbTA0");
        list.add("00B0dmO3pl");
        list.add("00B0nypiPq");
        list.add("00B0sOFgGl");
        list.add("00B0xqMvFX");
        list.add("00B4oGlNsw");
        list.add("00B5VtPWwS");
        list.add("00B5hGQQLl");
        list.add("00B6tHkGvl");
        list.add("00B97zXduz");
        list.add("00B99NaISp");
        list.add("00BBS4sEMS");
        list.add("00BCQRlReX");
        list.add("00BCvlvJnB");
        list.add("00BDpgCwK6");
        list.add("00BDwojFDS");
        list.add("00BJENxViU");
        list.add("00BJGl5cGk");
        list.add("00BJwtk1xX");
        list.add("00BK63v030");
        list.add("00BM3vkDEL");
        list.add("00BO6PKsFc");
        list.add("00BOf4tJlm");
        list.add("00BOw3omQO");
        list.add("00BQUlqIKP");
        list.add("00BRS9oHl0");
        list.add("00BRsgC65X");
        list.add("00BSu8mklM");
        list.add("00BTxllugl");
        list.add("00BU41RkEt");
        list.add("00BVrjxN0l");
        list.add("00BWgJavKd");
        list.add("00BaHrkCgk");
        list.add("00Bb4klolM");
        list.add("00BbIAp4Dl");
        list.add("00Bccbl6wF");
        list.add("00Bcum2lSr");
        list.add("00BdRnRukQ");
        list.add("00Bfn1lNFj");
        list.add("00BktVmiAv");
        list.add("00BlC8ypO4");
        list.add("00BlQk2kVl");
        list.add("00BlcLldus");
        list.add("00BlkSewle");
        list.add("00BlllmEjE");
        list.add("00BlmioQSm");
        list.add("00BlryNval");
        list.add("00Bml0Nywd");
        list.add("00BmtRpxlk");
        list.add("00BpXVMGi9");
        list.add("00Bplkh2Kl");
        list.add("00BpqlMljl");
        list.add("00Bqmlweuk");
        list.add("00BsqwUSeT");
        list.add("00Bsygl4wt");
        list.add("00BtImIl23");
        list.add("00Btklll4f");
        list.add("00BuV0PlZI");
        list.add("00BwMQL4pv");
        list.add("00BxPtl4ld");
        list.add("00By0ATnol");
        list.add("00C09stPfn");
        list.add("00C0mklM4l");
        list.add("00C0nlrLlw");
        list.add("00C1GuJll3");
        list.add("00C5llQl7s");
        list.add("00C5xklllp");
        list.add("00CABeUVvs");
        list.add("00CAqu6RQF");
        list.add("00CB2nulB2");
        list.add("00CCeaHx8H");
        list.add("00CFDvqGMi");
        list.add("00CItC7xrn");
        list.add("00CMaEkQ8P");
        list.add("00CN02Mbll");
        list.add("00CPJv3vwm");
        list.add("00CRigOTn7");
        list.add("00CVF9f0zP");
        list.add("00CaXOptfd");
        list.add("00Cb5p2kg4");
        list.add("00CcD0mwri");
        list.add("00CclpmZ57");
        list.add("00Cilho3om");
        list.add("00CjSWl9xl");
        list.add("00CkloGmQB");
        list.add("00Ckmk3Hc4");
        list.add("00Cl1kJEBl");
        list.add("00Cl40JAph");
        list.add("00ClDOf02s");
        list.add("00ClKbWmQv");
        list.add("00ClQ1lda6");
        list.add("00ClR3Ciiy");
        list.add("00ClbCMlgl");
        list.add("00CleTEr2w");
        list.add("00Cll0tlUb");
        list.add("00ClsKvjND");
        list.add("00Cm10lsvk");
        list.add("00Cm5lYGmJ");
        list.add("00CmWgwJ5l");
        list.add("00Cmmk0Asl");
        list.add("00Cmmlillu");
        list.add("00Cmndlldw");
        list.add("00CmzmoYay");
        list.add("00CmzrxTkO");
        list.add("00CnMLrlur");
        list.add("00CpJWn2iY");
        list.add("00CqNW2rRl");
        list.add("00Cql5wRLR");
        list.add("00CtAlmOVp");
        list.add("00CtyUmuky");
        list.add("00CummISNF");
        list.add("00CuvX9uB0");
        list.add("00CvHczlF0");
        list.add("00CwGKs77n");
        list.add("00Cwh0klxl");
        list.add("00D21mRgSz");
        list.add("00D3XwNQBm");
        list.add("00D4F0jlOA");
        list.add("00D5bltpll");
        list.add("00D7AtvPKj");
        list.add("00D7wspuZP");
        list.add("00D9C8IJix");
        list.add("00DCltjdom");
        list.add("00DDJd3ett");
        list.add("00DDrzEsAi");
        list.add("00DF7D3zlL");
        list.add("00DG2lzkeK");
        list.add("00DHzuzKQN");
        list.add("00DITucgpt");
        list.add("00DJSzq4H0");
        list.add("00DJll49J6");
        list.add("00DLSWlSn1");
        list.add("00DMHvpkLm");
        list.add("00DMk1lStZ");
        list.add("00DMwWul3M");
        list.add("00DNRXWwlT");
        list.add("00DQLeNWte");
        list.add("00DR4Rftll");
        list.add("00DTMalqQa");
        list.add("00DU0lk6t0");
        list.add("00DUDuo8lw");
        list.add("00DVE2csMt");
        list.add("00DW3ZEerX");
        list.add("00DW6QKmk1");
        list.add("00DYHlrial");
        list.add("00DZGdRTlf");
        list.add("00DZwmlYq1");
        list.add("00DbMRIOlB");
        list.add("00DbldpPE0");
        list.add("00DdllKwqn");
        list.add("00DelTsktl");
        list.add("00DeldsICn");
        list.add("00DelnECbk");
        list.add("00DfYyOkkM");
        list.add("00DfldPVRm");
        list.add("00Dj4rtBCv");
        list.add("00DjSmjSdS");
        list.add("00DklTSH1P");
        list.add("00DkmlxmR1");
        list.add("00DlJ3QlWw");
        list.add("00DlKwcxkK");
        list.add("00DlV8FemG");
        list.add("00DlYlmQe0");
        list.add("00DlllOSZ5");
        list.add("00DlmG5mrl");
        list.add("00DltwOZll");
        list.add("00DlwYmmlN");
        list.add("00DmgVlXIq");
        list.add("00DmmcwpTu");
        list.add("00DnJAZQmu");
        list.add("00DnNivJXl");
        list.add("00DnklrL54");
        list.add("00DnlAyZuE");
        list.add("00DnlltMlq");
        list.add("00DnvbllUK");
        list.add("00DoHxEA0r");
        list.add("00DoXQlKlL");
        list.add("00DpkTIzlA");
        list.add("00DpwBerYn");
        list.add("00Dpzklnxk");
        list.add("00DqkyayZp");
        list.add("00DqlHkgbl");
        list.add("00Drbg05dt");
        list.add("00Dsllulla");
        list.add("00DuJyrDlG");
        list.add("00Dv88daPZ");
        list.add("00DvCA1wjk");
        list.add("00Dw6lK6L0");
        list.add("00DwOQZ2l2");
        list.add("00DwQSJyRl");
        list.add("00DxHesGJV");
        list.add("00DylmdXzq");
        list.add("00E3rZctbc");
        list.add("00E4aQKksb");
        list.add("00E59tl2lv");
        list.add("00E6a0KxE6");
        list.add("00E6wuqIHD");
        list.add("00E7QrkESi");
        list.add("00E7cNNIkX");
        list.add("00E9WJWa0J");
        list.add("00EAivoPls");
        list.add("00EAoDMHll");
        list.add("00ECKIUpBc");
        list.add("00ECkOlysZ");
        list.add("00EDRqf7m3");
        list.add("00EDecD9MR");
        list.add("00EDl3lmkB");
        list.add("00EDnmJplC");
        list.add("00EElEsv0l");
        list.add("00EFCEluap");
        list.add("00EFkloX5b");
        list.add("00EFl1AtXx");
        list.add("00EIXCxMf7");
        list.add("00EJl64lzc");
        list.add("00EJrlUBly");
        list.add("00EOwvvdcH");
        list.add("00EQRYlvqM");
        list.add("00EQl0X6ht");
        list.add("00EQlLfsQW");
        list.add("00ERu00nk0");
        list.add("00ESLlddau");
        list.add("00ESuuq6oq");
        list.add("00EWunslXl");
        list.add("00EWvdmhZE");
        list.add("00EX48volg");
        list.add("00EXN0MbMT");
        list.add("00EXlptoXl");
        list.add("00EbdYhXJa");
        list.add("00EcyuTHYl");
        list.add("00EelhlVwR");
        list.add("00EellMwtn");
        list.add("00EfHWKCX4");
        list.add("00EfRPkujB");
        list.add("00EfjQ0skB");
        list.add("00EflUksul");
        list.add("00EiP9swh2");
        list.add("00Eisnhkq0");
        list.add("00EjAMfllY");
        list.add("00Ek44lUwb");
        list.add("00Ek4klgyY");
        list.add("00EkFO68mQ");
        list.add("00EkP4fdxv");
        list.add("00EkXl7mXp");
        list.add("00EkdNrZJ6");
        list.add("00EkkmG9mZ");
        list.add("00Eksll4S9");
        list.add("00El99CBQZ");
        list.add("00ElIXSlY0");
        list.add("00ElNcwlwR");
        list.add("00ElcPaAlR");
        list.add("00Ell0VnTl");
        list.add("00EmWy1chs");
        list.add("00Emcl2QQs");
        list.add("00EniEltPh");
        list.add("00Enkt9mrG");
        list.add("00EoM6lpZf");
        list.add("00EojCsum0");
        list.add("00EqOGa8SZ");
        list.add("00EqUtEslm");
        list.add("00Eqcm82yG");
        list.add("00ErS6yFSs");
        list.add("00Es4eCQmQ");
        list.add("00EsAMs8ql");
        list.add("00EsQRl98m");
        list.add("00EsmNQx3l");
        list.add("00EsoSyel4");
        list.add("00EuITjlFe");
        list.add("00EuO9klD8");
        list.add("00EukAqllp");
        list.add("00EumFZuC7");
        list.add("00EvLmVyt7");
        list.add("00EvmloAzr");
        list.add("00EykQJdlS");
        list.add("00EypOaKI4");
        list.add("00EyqsTmfl");
        list.add("00F8qEhxPY");
        list.add("00F9WlxTYl");
        list.add("00FB39EuWu");
        list.add("00FDiQ0T7l");
        list.add("00FEwMblr3");
        list.add("00FImHHUoK");
        list.add("00FJ1mLaTG");
        list.add("00FRM8ylOR");
        list.add("00FRqm07sF");
        list.add("00FSsVKk4t");
        list.add("00FUelHA7S");
        list.add("00FW6xny3H");
        list.add("00FWllWkDQ");
        list.add("00FYT8qm9s");
        list.add("00FYxlFxsl");
        list.add("00FYzw7Vs1");
        list.add("00FdvoR4ma");
        list.add("00FfKnllOP");
        list.add("00FhK3Xjl4");
        list.add("00FidCkEQJ");
        list.add("00FkMlllPn");
        list.add("00FkngkPpp");
        list.add("00FkwhmD4l");
        list.add("00FkwwvakD");
        list.add("00Fl1vPZSl");
        list.add("00FlkTHmm7");
        list.add("00Fm6mWfIN");
        list.add("00FmGyKf2s");
        list.add("00FnDI0Eq0");
        list.add("00FnY0doWh");
        list.add("00FpSJe6Fd");
        list.add("00Fr3m9kyq");
        list.add("00FsbItdXe");
        list.add("00FsmMzopP");
        list.add("00FtufeN2H");
        list.add("00Ftztrllw");
        list.add("00FuIlDBpE");
        list.add("00FuakKwXi");
        list.add("00FuqwVmll");
        list.add("00FxcJOdRk");
        list.add("00Fybxllsl");
        list.add("00FzlMlv0J");
        list.add("00Fzqli2mv");
        list.add("00G1evlE8M");
        list.add("00G4chTlRg");
        list.add("00G53U7yVj");
        list.add("00G7GQJA0K");
        list.add("00GDlIzGMi");
        list.add("00GHCLohOk");
        list.add("00GIH5kySs");
        list.add("00GJdr4ClG");
        list.add("00GMavkoln");
        list.add("00GNvozls5");
        list.add("00GPH6el2A");
        list.add("00GPsY45c4");
        list.add("00GR9D9lPS");
        list.add("00GRkR1OYl");
        list.add("00GRklKvti");
        list.add("00GRnlLBbe");
        list.add("00GTk0m1aM");
        list.add("00GUMurXTl");
        list.add("00GV8YElAM");
        list.add("00GWntylst");
        list.add("00GZh4Luyf");
        list.add("00GcNLvXms");
        list.add("00GetOcpaR");
        list.add("00Gf0mbzhM");
        list.add("00GhsRkUcM");
        list.add("00Gk4nllRE");
        list.add("00GkJiMnQX");
        list.add("00GkPlSulh");
        list.add("00GklzmnNg");
        list.add("00GlBlfpkg");
        list.add("00GlTlK8su");
        list.add("00GlXoP8Bp");
        list.add("00GlgawkxZ");
        list.add("00GlgolmHP");
        list.add("00GllZkYsD");
        list.add("00Gllfig8l");
        list.add("00GlpfdYsm");
        list.add("00Gm9PQP7t");
        list.add("00GoIlaRSo");
        list.add("00GpSARq0z");
        list.add("00GqGkAlUD");
        list.add("00GrM3rles");
        list.add("00GslclfXA");
        list.add("00GsonpdRw");
        list.add("00GsozaS7m");
        list.add("00GulvrfLk");
        list.add("00GwQszIpq");
        list.add("00GyX9ssll");
        list.add("00H0TaAAlv");
        list.add("00H0kViMyQ");
        list.add("00H14lRFly");
        list.add("00H2lMvmnU");
        list.add("00H32SvElr");
        list.add("00H6V2xelX");
        list.add("00H76sv9kl");
        list.add("00H8OVcgEW");
        list.add("00H8nRIeNF");
        list.add("00HDaRO3oN");
        list.add("00HEruluZq");
        list.add("00HGSmD7Er");
        list.add("00HJusaamz");
        list.add("00HKzmLrku");
        list.add("00HLQSDQWm");
        list.add("00HLxmv6TS");
        list.add("00HS4lvlnk");
        list.add("00HVwdwJl3");
        list.add("00HWVrYlml");
        list.add("00HXYikmDR");
        list.add("00HXzN2qxl");
        list.add("00HYlftm0g");
        list.add("00HbozlmOz");
        list.add("00HcUy6ycs");
        list.add("00HelcxRdl");
        list.add("00Hk6yVbME");
        list.add("00HkQAnqCQ");
        list.add("00HlVlysmz");
        list.add("00HllGsolz");
        list.add("00HllHuOm6");
        list.add("00HlzqAJkv");
        list.add("00HoAbQ8Gu");
        list.add("00Hp77eFyl");
        list.add("00Hpwuypsw");
        list.add("00HqhmPnZQ");
        list.add("00HsgluAwo");
        list.add("00HsvP00Ss");
        list.add("00HuMiOzQA");
        list.add("00HxbVtNBD");
        list.add("00HzhWDzvP");
        list.add("00Hzl85DXj");
        list.add("00I1nwLNlz");
        list.add("00I3lWekll");
        list.add("00I7x22o12");
        list.add("00I8Cmf3nm");
        list.add("00I9lFiduy");
        list.add("00IADPv9an");
        list.add("00ICl8lvet");
        list.add("00IDZlOYxr");
        list.add("00IGvLNYyO");
        list.add("00IHmk1iqQ");
        list.add("00IHq7MFrW");
        list.add("00IJQwYozX");
        list.add("00IJUZJAbk");
        list.add("00IKl2lk6m");
        list.add("00ILHWSzcp");
        list.add("00IPSjpzXO");
        list.add("00IQlvxHal");
        list.add("00IS4A9c51");
        list.add("00ISncmflO");
        list.add("00ITllmngt");
        list.add("00IWUTJ36l");
        list.add("00IZBJ0pwB");
        list.add("00IcRmIzME");
        list.add("00IcrjimGG");
        list.add("00Ie2meQll");
        list.add("00IeTloIlf");
        list.add("00IfFEklnD");
        list.add("00IfUmY4ls");
        list.add("00IfVAPRvW");
        list.add("00IfldWrnq");
        list.add("00IjsjOfyp");
        list.add("00Ik7MAllk");
        list.add("00IkRlBS0l");
        list.add("00Ikp1miKw");
        list.add("00IkywZZT2");
        list.add("00IlFjtvkS");
        list.add("00IlYALl2l");
        list.add("00IlbS2Ayj");
        list.add("00IllileMA");
        list.add("00IlllLfug");
        list.add("00ImiTYem2");
        list.add("00Imr7leKS");
        list.add("00ImrPSelA");
        list.add("00In2kh9mC");
        list.add("00Iom0jIls");
        list.add("00IqWIXIuF");
        list.add("00IsltmLKm");
        list.add("00It4KlZul");
        list.add("00ItUo3sh3");
        list.add("00ItY9a5KD");
        list.add("00ItYGk0Fb");
        list.add("00IuoilNIR");
        list.add("00IwLoRI3k");
        list.add("00Ixj20xA0");
        list.add("00Iy9o7ufb");
        list.add("00J0lwAJol");
        list.add("00J2xxo5iu");
        list.add("00J3YkFrvY");
        list.add("00J3llKlDu");
        list.add("00J6iQoAcn");
        list.add("00J90lYvlS");
        list.add("00JAnmvllF");
        list.add("00JBpGtNF2");
        list.add("00JBzyzQvo");
        list.add("00JDlJswkO");
        list.add("00JEll0RP0");
        list.add("00JF0loReB");
        list.add("00JGuXUiW4");
        list.add("00JJCyQlf4");
        list.add("00JMEF4ljl");
        list.add("00JN7lkssJ");
        list.add("00JNQylxW6");
        list.add("00JNlOwZkw");
        list.add("00JNmBCtEf");
        list.add("00JOsl8MJk");
        list.add("00JPbkwEnw");
        list.add("00JQLx11Fr");
        list.add("00JQP0wyPv");
        list.add("00JQglgKpA");
        list.add("00JRAlRSK3");
        list.add("00JSoQsvxi");
        list.add("00JSzmUflm");
        list.add("00JTA4GNvh");
        list.add("00JVQkHVYT");
        list.add("00JVyvSLGl");
        list.add("00JWmidFul");
        list.add("00JYysl1hO");
        list.add("00JZJpzull");
        list.add("00JZRyvmIl");
        list.add("00Jb0z7Npq");
        list.add("00JcLRwwtW");
        list.add("00Jd8lIZKP");
        list.add("00JeA7YCmh");
        list.add("00Jeyucl0T");
        list.add("00Jf1sxaEu");
        list.add("00JfuGMBt4");
        list.add("00JiomnxlG");
        list.add("00JjonqlWl");
        list.add("00JkhLo1tz");
        list.add("00Jkn6mXID");
        list.add("00Jl1ds4bl");
        list.add("00JlQ0akvl");
        list.add("00JljBOQjt");
        list.add("00JlsRbjS4");
        list.add("00Jlumqqrv");
        list.add("00JnB91dfd");
        list.add("00Jnmvtlqz");
        list.add("00Joldt2hG");
        list.add("00Jolhk0lu");
        list.add("00Jollwakg");
        list.add("00Jq50luQ4");
        list.add("00JqUlzx1P");
        list.add("00JtAppthX");
        list.add("00JttpsPCK");
        list.add("00JuclJuM6");
        list.add("00Jv0VOM8l");
        list.add("00JvRnsufN");
        list.add("00JwOpiNBK");
        list.add("00Jwalnjml");
        list.add("00JwsmlicK");
        list.add("00JykYeoKA");
        list.add("00JzSeeWwJ");
        list.add("00JznGfxNU");
        list.add("00K0FTsc0B");
        list.add("00K0xEJyTO");
        list.add("00K3klsQl8");
        list.add("00K4plDkpB");
        list.add("00K6EB4Mlg");
        list.add("00K8APM3xM");
        list.add("00K9mDAIev");
        list.add("00KEqqc7Vq");
        list.add("00KEraTlol");
        list.add("00KFxwmkhr");
        list.add("00KJoyfuah");
        list.add("00KKNExe8l");
        list.add("00KKyCl3iu");
        list.add("00KLUG8NZa");
        list.add("00KPJ7EYlQ");
        list.add("00KSlmuvdz");
        list.add("00KTik9Nlp");
        list.add("00KVMMnlib");
        list.add("00KWlSxsHN");
        list.add("00KZXlskLF");
        list.add("00KbZzhMls");
        list.add("00Kbn5ShKl");
        list.add("00KbsG4ua5");
        list.add("00Kc2W52c8");
        list.add("00KcJhupLn");
        list.add("00KdFlGXAS");
        list.add("00KdVkGfCZ");
        list.add("00KeulPEmD");
        list.add("00Kfljg6sm");
        list.add("00Kg4F4cIo");
        list.add("00Kh8DzlMU");
        list.add("00KinXLXlR");
        list.add("00Kl30xlDy");
        list.add("00Kl6ilxkl");
        list.add("00KlB0vDxE");
        list.add("00KlBABlnd");
        list.add("00KlDJml2D");
        list.add("00KlMltDkf");
        list.add("00KllrlxSl");
        list.add("00Klmv3nO0");
        list.add("00KlrSYfCz");
        list.add("00KltnuVW7");
        list.add("00KlwulmW5");
        list.add("00Km08sv0r");
        list.add("00KmuplSIn");
        list.add("00KqQlnIlM");
        list.add("00KqblF3lZ");
        list.add("00Kqnk84DE");
        list.add("00Kqnlrugp");
        list.add("00KrplsGSJ");
        list.add("00KsloEbU1");
        list.add("00Ksre8WUN");
        list.add("00KuflZSlr");
        list.add("00Kxcrks7F");
        list.add("00KxlbUMA7");
        list.add("00KyivzoNl");
        list.add("00KylYSaKY");
        list.add("00KzsolpmQ");
        list.add("00KzzxlMmI");
        list.add("00L0zdRHz8");
        list.add("00L5EnZk1M");
        list.add("00L5bMmU00");
        list.add("00L6zkkASk");
        list.add("00L9IlMy0D");
        list.add("00LFllMB4w");
        list.add("00LFn4Mlvq");
        list.add("00LFsdlk4h");
        list.add("00LImEI0M3");
        list.add("00LKlxk7WH");
        list.add("00LMTl8SDl");
        list.add("00LMlmD4KQ");
        list.add("00LMpzmlry");
        list.add("00LNNmlWpw");
        list.add("00LOfU4llw");
        list.add("00LQi4i0mv");
        list.add("00LQrliOml");
        list.add("00LTskSGEg");
        list.add("00LUwCTfP0");
        list.add("00LVUeclkW");
        list.add("00LVyn1Bsq");
        list.add("00LXAnuo8A");
        list.add("00LXVBmZme");
        list.add("00LXlonAUl");
        list.add("00LYqlglfl");
        list.add("00LZO0xlaq");
        list.add("00LZlVc3nO");
        list.add("00LaiIn2dV");
        list.add("00Lcnu40np");
        list.add("00Ldej6N0h");
        list.add("00Le4yZlcP");
        list.add("00LgDulYEu");
        list.add("00LhlllA4l");
        list.add("00LiRRlUyI");
        list.add("00LiWkQpmm");
        list.add("00Lk7xlC6D");
        list.add("00LkxtvlcB");
        list.add("00Ll3sJ0le");
        list.add("00LlBltEHm");
        list.add("00LlCJknjp");
        list.add("00LlFlH0As");
        list.add("00LlllEYvu");
        list.add("00LltoekO4");
        list.add("00LlxlpIl3");
        list.add("00LlylMoHs");
        list.add("00LmFpjtMP");
        list.add("00LnTJqSnl");
        list.add("00LoirlX5w");
        list.add("00LrFFtztk");
        list.add("00LrvPlQN4");
        list.add("00LsOZ4Bn6");
        list.add("00Lt7tzGus");
        list.add("00LtmxT9io");
        list.add("00LupfF8il");
        list.add("00Lvqnt0lp");
        list.add("00Lvw2nBSW");
        list.add("00M1hkmtTl");
        list.add("00M4EPlIcl");
        list.add("00M4vtJ6ni");
        list.add("00M5lokkK0");
        list.add("00M5wZlcZM");
        list.add("00M7cefmYS");
        list.add("00M9mlOzB4");
        list.add("00MAEim464");
        list.add("00MAlywV9v");
        list.add("00MBYavlrt");
        list.add("00MBwd4SBS");
        list.add("00MC2kc7lB");
        list.add("00MC4WEOGg");
        list.add("00MDEDnkTg");
        list.add("00MDXalnkl");
        list.add("00MEl7sldJ");
        list.add("00MFl1EgZ5");
        list.add("00MHf0laxe");
        list.add("00MIvtHrI2");
        list.add("00MJvlmUww");
        list.add("00MKPnlb4u");
        list.add("00MM7lIRxm");
        list.add("00MMglywlo");
        list.add("00MNKlKgzZ");
        list.add("00MNlnRJ3c");
        list.add("00MPkMowF1");
        list.add("00MPmZ5SsU");
        list.add("00MRlkflE4");
        list.add("00MSJdNyCx");
        list.add("00MSQDNlrQ");
        list.add("00MSRnkQ9t");
        list.add("00MSwl4fJZ");
        list.add("00MUOHMljC");
        list.add("00MVsxNs8Q");
        list.add("00MWDmCmrb");
        list.add("00MXvmwgrl");
        list.add("00MYZKlssk");
        list.add("00MYlkQyps");
        list.add("00MYxRKW3M");
        list.add("00MaOlrlVD");
        list.add("00MbkEymap");
        list.add("00MbllrWlz");
        list.add("00MbrpJlH8");
        list.add("00McMeSPSs");
        list.add("00Md4PhrMw");
        list.add("00MdyXlf2i");
        list.add("00Megk3yNl");
        list.add("00Memk5lYZ");
        list.add("00MfSlxuNl");
        list.add("00Mj6setuS");
        list.add("00MkglcAmZ");
        list.add("00Mkgom7EK");
        list.add("00MkmkGlBy");
        list.add("00MkyGGzjb");
        list.add("00MlT7LlES");
        list.add("00MlWbracw");
        list.add("00MlmWolwI");
        list.add("00Mlsylbgf");
        list.add("00MlzNvFfp");
        list.add("00Mlzll7J4");
        list.add("00Mmehml3t");
        list.add("00MmlxyIJK");
        list.add("00MmmT5bzq");
        list.add("00MmmxM0mx");
        list.add("00MnV4Ll5x");
        list.add("00Mnulllkw");
        list.add("00Mqo0lDAv");
        list.add("00MshRlYQk");
        list.add("00MtXlQsjl");
        list.add("00MteMEGQa");
        list.add("00Mu9w3NGA");
        list.add("00MuClEKmc");
        list.add("00MvYummT6");
        list.add("00MwRexAlt");
        list.add("00MwXSlj4O");
        list.add("00MwZnm3mm");
        list.add("00MwblGmlB");
        list.add("00MwlIeL7F");
        list.add("00MwllaT0I");
        list.add("00MwtRwULl");
        list.add("00MwtiwwJc");
        list.add("00My81mtj3");
        list.add("00Mykl1KDZ");
        list.add("00Mzm7phFu");
        list.add("00N07dM3tH");
        list.add("00N2YBlVDl");
        list.add("00N47TlwQ2");
        list.add("00N4l4Qnke");
        list.add("00N4vOllXs");
        list.add("00N5l77T6p");
        list.add("00N9ygOyl1");
        list.add("00NBpLGO8F");
        list.add("00NCIltKcj");
        list.add("00NDllGYKe");
        list.add("00NGls6llq");
        list.add("00NJk4KOWu");
        list.add("00NJkuspvd");
        list.add("00NM4VrolD");
        list.add("00NMPkmQg8");
        list.add("00NNlZtlpJ");
        list.add("00NSDn2Nmi");
        list.add("00NSffl7M4");
        list.add("00NSlSfXll");
        list.add("00NSvSrxcl");
        list.add("00NTetM0pl");
        list.add("00NUAqzlFj");
        list.add("00NYy3j5vi");
        list.add("00NalSmMFZ");
        list.add("00Nbr7KlJu");
        list.add("00NcQHxOuk");
        list.add("00NcXsg8zI");
        list.add("00NcsSS8Gl");
        list.add("00NeUvKVCl");
        list.add("00NfSjsSUQ");
        list.add("00NfvElmOl");
        list.add("00NgevyPuH");
        list.add("00NgslnSlO");
        list.add("00NhlzNSCc");
        list.add("00NhpmFTpc");
        list.add("00NiilsQtD");
        list.add("00NilEj31d");
        list.add("00NilZnONS");
        list.add("00NkJAulml");
        list.add("00NkjPMlpG");
        list.add("00NlSk0ckc");
        list.add("00NlXxWlvd");
        list.add("00NlYlnGcQ");
        list.add("00NllBNsE0");
        list.add("00NlmUlsmm");
        list.add("00Nlqy0Pum");
        list.add("00Nlsv01lz");
        list.add("00NmUmsKkn");
        list.add("00NosTRWO0");
        list.add("00NpDwqZqn");
        list.add("00Nq0aUYk8");
        list.add("00NsVj7DMu");
        list.add("00NslhunH1");
        list.add("00NtnTgn5F");
        list.add("00NuBaplmk");
        list.add("00NyUHAmst");
        list.add("00Nzbdglll");
        list.add("00NzyBreTU");
        list.add("00O0tjiIb6");
        list.add("00O4l9lUvi");
        list.add("00O6U2XiQO");
        list.add("00O7dYBmLz");
        list.add("00OA0pC0lK");
        list.add("00OA4VEtcS");
        list.add("00OBm80l2k");
        list.add("00OCwutHdT");
        list.add("00ODwktlPl");
        list.add("00OKslHVdM");
        list.add("00OMolZDdU");
        list.add("00OO646Roo");
        list.add("00OOFD1sty");
        list.add("00OOxlAlz0");
        list.add("00OQt4xkSI");
        list.add("00ORuud1oQ");
        list.add("00OU0Yafiu");
        list.add("00OUNDvcnG");
        list.add("00OUOQYceg");
        list.add("00OUuaCbIy");
        list.add("00OWxFHPlE");
        list.add("00OYwRwTnD");
        list.add("00OZFYnfgw");
        list.add("00OZWX1okw");
        list.add("00OcOu2lnD");
        list.add("00OdkAl1ls");
        list.add("00Oe5luzYk");
        list.add("00Oell03mR");
        list.add("00Oeom9SwI");
        list.add("00Og07dbbl");
        list.add("00OgllkBlE");
        list.add("00OgxzDlCi");
        list.add("00OhSnlukb");
        list.add("00Ok8t6agn");
        list.add("00Oks8ql3d");
        list.add("00Ol0hRmmd");
        list.add("00Ol6v0cu0");
        list.add("00OlCLunVI");
        list.add("00OlZRDFnc");
        list.add("00Olollcxi");
        list.add("00Omak03yk");
        list.add("00Ommlulyl");
        list.add("00OoPsgyxl");
        list.add("00OpPkpmgg");
        list.add("00OsUlw4Kb");
        list.add("00OuBMllDf");
        list.add("00OuEitQtm");
        list.add("00OvFCSAwl");
        list.add("00OvNJFg8l");
        list.add("00OyhxtlIr");
        list.add("00P1c6lQ0I");
        list.add("00P1mbm2ma");
        list.add("00P3IlPlin");
        list.add("00P3lVRzrp");
        list.add("00P4ll8pSW");
        list.add("00P6KlJlP9");
        list.add("00PEKBCFFx");
        list.add("00PF8Euhwd");
        list.add("00PK7lEKhb");
        list.add("00PMwcSl8n");
        list.add("00PNEFZIBV");
        list.add("00PNbyEEmi");
        list.add("00PNhI7VZI");
        list.add("00PPmeK4ND");
        list.add("00PQpjAtZa");
        list.add("00PSpzAlAw");
        list.add("00PTAuk5GO");
        list.add("00PTprmscl");
        list.add("00PUkY2PKi");
        list.add("00PVStvlky");
        list.add("00PVuGllRM");
        list.add("00PXdpXtjy");
        list.add("00PY3RRxDw");
        list.add("00PdNcun3w");
        list.add("00PemksSlk");
        list.add("00Pf0lklkt");
        list.add("00PgydlMWq");
        list.add("00PjaKXfBl");
        list.add("00PkcZJ4yl");
        list.add("00PklrwcWk");
        list.add("00PlEYkf3M");
        list.add("00PlRut9le");
        list.add("00PlaklQwK");
        list.add("00Plf4mmbk");
        list.add("00PlmjlR8m");
        list.add("00PlofO2Fr");
        list.add("00PmEql6Re");
        list.add("00PmPtpCku");
        list.add("00PmjZGlZo");
        list.add("00PmolAOz0");
        list.add("00Pnllsnvz");
        list.add("00Pp5klpl6");
        list.add("00PqJwllLl");
        list.add("00Pr1lNO9k");
        list.add("00PrmCYFz4");
        list.add("00PsFHVWjD");
        list.add("00PsqQS0qQ");
        list.add("00Ptm4lLCm");
        list.add("00Px9lKmZP");
        list.add("00PxllonGQ");
        list.add("00PzvullFl");
        list.add("00Q0HmRAPm");
        list.add("00Q0l3cl9t");
        list.add("00Q0lyuMlO");
        list.add("00Q0vKwswv");
        list.add("00Q2LlJpJD");
        list.add("00Q2a8AlOq");
        list.add("00Q3Wc93TE");
        list.add("00Q4cUbB8e");
        list.add("00Q7PmT2ll");
        list.add("00Q7m3mlkf");
        list.add("00Q7sfHnVG");
        list.add("00Q8muIG67");
        list.add("00Q8x4XdUn");
        list.add("00Q92lulmW");
        list.add("00QAQvz4Qu");
        list.add("00QB6lb5wk");
        list.add("00QCscPYnb");
        list.add("00QDEfdQvB");
        list.add("00QEL6dQyh");
        list.add("00QEMgllCk");
        list.add("00QEeCi0Jy");
        list.add("00QIJomltu");
        list.add("00QKIUluKg");
        list.add("00QKJjDwAs");
        list.add("00QOkivs5A");
        list.add("00QQGhgn0l");
        list.add("00QQrKw3zf");
        list.add("00QRk8RCC9");
        list.add("00QS0OkxlK");
        list.add("00QSklm9sM");
        list.add("00QSnQbckc");
        list.add("00QT4wl9kI");
        list.add("00QW2cp3uY");
        list.add("00QXUtnjSY");
        list.add("00QY75Vlkv");
        list.add("00QZS5DCwz");
        list.add("00QZkkA6mw");
        list.add("00QaCOsuvR");
        list.add("00QarElynl");
        list.add("00QbasllvR");
        list.add("00QbxX8AJz");
        list.add("00QcEKplMv");
        list.add("00QebWubEi");
        list.add("00QffOktPO");
        list.add("00Qggl2pa8");
        list.add("00QkIZ5nln");
        list.add("00QkbTc8km");
        list.add("00Ql7WBnVm");
        list.add("00QlJl8xQs");
        list.add("00QlSpuWgw");
        list.add("00Qlallaow");
        list.add("00QlcmwssP");
        list.add("00Qlel8lcH");
        list.add("00QljQxRN5");
        list.add("00QlwZZcJw");
        list.add("00QlyUu3yl");
        list.add("00QmJlxHmJ");
        list.add("00QmlT3DAB");
        list.add("00Qmvh3qlz");
        list.add("00QnQNEElN");
        list.add("00QomxVn2I");
        list.add("00QpfqsC54");
        list.add("00QplUlNiG");
        list.add("00QrSigari");
        list.add("00QsOlhaMs");
        list.add("00Qsl9srli");
        list.add("00QsrAkMol");
        list.add("00QuYYsSJt");
        list.add("00QuflelpN");
        list.add("00QvU8l3pL");
        list.add("00QwE33lzE");
        list.add("00QwWtliS8");
        list.add("00QwdMcklM");
        list.add("00Qx3WSlaG");
        list.add("00Qx7uejtL");
        list.add("00QxYNX5KT");
        list.add("00QyLEMllz");
        list.add("00QyflS7jM");
        list.add("00QyoQ7lAw");
        list.add("00R34tmnll");
        list.add("00R4lCajkI");
        list.add("00R9RbLytt");
        list.add("00RAlKSJOk");
        list.add("00RAutslnB");
        list.add("00RDnj5Lx9");
        list.add("00REvm4fRs");
        list.add("00REx6lRli");
        list.add("00RFOlvlXK");
        list.add("00RFe51Stu");
        list.add("00RFl0vclK");
        list.add("00RFndJ3wk");
        list.add("00RIJxiuqH");
        list.add("00RIl1Bsvm");
        list.add("00RJwCkmYX");
        list.add("00RKefZbgl");
        list.add("00RPqMl8yc");
        list.add("00RQa2sqpR");
        list.add("00RRANfkgq");
        list.add("00RUrlCi4r");
        list.add("00RelWlaTf");
        list.add("00Rhvz7bJf");
        list.add("00RimiQMlm");
        list.add("00Rk20nELl");
        list.add("00RkRixfUR");
        list.add("00RklppFYB");
        list.add("00RknDYltL");
        list.add("00RlBsjMt2");
        list.add("00RlM0vxn0");
        list.add("00RlQ7LGXl");
        list.add("00RlVktmcl");
        list.add("00RlbslJmT");
        list.add("00RlioRwIm");
        list.add("00RmtQlreY");
        list.add("00RnalulwQ");
        list.add("00RnrcliSD");
        list.add("00RovKllmE");
        list.add("00RppN5Orq");
        list.add("00Rs99nW0H");
        list.add("00Rsu0jyl2");
        list.add("00RtMFnlFg");
        list.add("00RtdvyWYo");
        list.add("00RuRD9e02");
        list.add("00RwULxi5E");
        list.add("00RwhsLm3j");
        list.add("00Rwlloyml");
        list.add("00RxDykqdx");
        list.add("00RxtlElIr");
        list.add("00RykGluFK");
        list.add("00RykwxkrZ");
        list.add("00S1ABxyu2");
        list.add("00S2IGBBlY");
        list.add("00S2sIfnE4");
        list.add("00S4WKlMji");
        list.add("00S5F1wSEH");
        list.add("00S6Gl0LMl");
        list.add("00S7umZEcl");
        list.add("00S8RsQswp");
        list.add("00S8clojqc");
        list.add("00S9slsDYa");
        list.add("00SBopMA9s");
        list.add("00SBu2FHUB");
        list.add("00SDkHlSlu");
        list.add("00SEMlkNOp");
        list.add("00SGmTum34");
        list.add("00SH5e9YEE");
        list.add("00SJlQo0xf");
        list.add("00SMlXviIS");
        list.add("00SOYHhqyy");
        list.add("00SOkl2n19");
        list.add("00SOy0lXwl");
        list.add("00SPjkeBZY");
        list.add("00SPpTDvsQ");
        list.add("00SPyLZCPl");
        list.add("00SRVkoQJv");
        list.add("00SSCul2u1");
        list.add("00STTUvziv");
        list.add("00STlAul8t");
        list.add("00SUcL3kyH");
        list.add("00SUw2e6ll");
        list.add("00SXOdSlZ4");
        list.add("00SYlJ2l9W");
        list.add("00SarW5mcp");
        list.add("00Sc7lk0lS");
        list.add("00SdlmuBDM");
        list.add("00SdxEkRq7");
        list.add("00SeX0huYl");
        list.add("00SecusZon");
        list.add("00Sf8zMtwv");
        list.add("00ShEXblwl");
        list.add("00ShZkyNQl");
        list.add("00SklBeElC");
        list.add("00Sl0Wmlmt");
        list.add("00Sl4DDnDl");
        list.add("00Sl7bPQbv");
        list.add("00SlK5R0kB");
        list.add("00SllHxkhk");
        list.add("00Sltnl4qN");
        list.add("00SluiIt5Q");
        list.add("00Sm8l2iMW");
        list.add("00Sm940xpm");
        list.add("00SmlllTsl");
        list.add("00SmlvkAwZ");
        list.add("00Snl3mm6l");
        list.add("00Snsflllm");
        list.add("00SpalyoFS");
        list.add("00SqmlzlQ3");
        list.add("00SrUkPllS");
        list.add("00SsJuaqVd");
        list.add("00StT2tuYj");
        list.add("00SteSDlmj");
        list.add("00StlnknR1");
        list.add("00Stt34okq");
        list.add("00Su81ZR1D");
        list.add("00SuTTJUR0");
        list.add("00Sv0Vynlu");
        list.add("00SvZlNm8I");
        list.add("00SwIw7Atl");
        list.add("00Swlc4SsA");
        list.add("00SwsEnQrM");
        list.add("00SxLFslNu");
        list.add("00SzJdpplx");
        list.add("00T0DaMSRW");
        list.add("00T0IL8yID");
        list.add("00T1HHIcHE");
        list.add("00T2lwXkwB");
        list.add("00T2pmuE1M");
        list.add("00T4YLcpAm");
        list.add("00T5nyVcnm");
        list.add("00T8ElcPVw");
        list.add("00T8Nm9mZs");
        list.add("00T8Smihxl");
        list.add("00TAMRk1uu");
        list.add("00TAllyiNl");
        list.add("00TBwlKqBX");
        list.add("00TCcCdMbn");
        list.add("00TF5rK7KA");
        list.add("00TGln4lK2");
        list.add("00TIjMLWDt");
        list.add("00TMl8mwOk");
        list.add("00TPy3Mwli");
        list.add("00TPznmXtt");
        list.add("00TQQ2Iqlg");
        list.add("00TQllysmY");
        list.add("00TQugjW90");
        list.add("00TSlvllyX");
        list.add("00TTkl7tws");
        list.add("00TUkphlmg");
        list.add("00TUnbSrkB");
        list.add("00TWv1lsas");
        list.add("00TalraqvD");
        list.add("00TatncLyU");
        list.add("00TdEgylSf");
        list.add("00TdUm5nas");
        list.add("00TeAveCYO");
        list.add("00Tj8tFwTn");
        list.add("00TkMdkXlS");
        list.add("00TkQyfSvs");
        list.add("00Tksc5zQx");
        list.add("00TlIQ5iww");
        list.add("00TlkTAslK");
        list.add("00TlkllrXO");
        list.add("00TllTxkpJ");
        list.add("00Tlmmmpp3");
        list.add("00TlntlAsk");
        list.add("00TmbiEMmB");
        list.add("00Tmlmqwgy");
        list.add("00Tnm4M2Mm");
        list.add("00ToOQjJWs");
        list.add("00TqIkE3wk");
        list.add("00TsKryBlk");
        list.add("00TtGTfI8E");
        list.add("00Tu16lFly");
        list.add("00TuQSZ7gt");
        list.add("00TuRd7HTJ");
        list.add("00TultyEMN");
        list.add("00Tunjl2NH");
        list.add("00Tw4aeypN");
        list.add("00TwJJwNHk");
        list.add("00TwmqyzIm");
        list.add("00Ty3TjauL");
        list.add("00TySV4abT");
        list.add("00TyTzMfl8");
        list.add("00U2flmAm7");
        list.add("00U33olwTG");
        list.add("00U5W2UkrJ");
        list.add("00U6aMNs3N");
        list.add("00U6eqwrbb");
        list.add("00U6m243fM");
        list.add("00U8lK1j0Z");
        list.add("00U9GlkYvM");
        list.add("00UDElblJ2");
        list.add("00UEl25XWv");
        list.add("00UEqhl0l4");
        list.add("00UFRGelSF");
        list.add("00UH8blof1");
        list.add("00UKlklgxj");
        list.add("00UQ7tMXcm");
        list.add("00UQluGGWl");
        list.add("00URQylllt");
        list.add("00UTlxTKan");
        list.add("00UU1sVZll");
        list.add("00UUrXwXN0");
        list.add("00UZru8Tkl");
        list.add("00UavGSxXj");
        list.add("00UblI7Qml");
        list.add("00Ubq9msNn");
        list.add("00UbrLAlQk");
        list.add("00Ue7KD0an");
        list.add("00UeluluLE");
        list.add("00UgblNqa5");
        list.add("00UixaGQSW");
        list.add("00UjPlOvow");
        list.add("00UklbiqUq");
        list.add("00Ukllkled");
        list.add("00UlKyMM4E");
        list.add("00UlMlxsxc");
        list.add("00Ulfq0n9J");
        list.add("00Ull4sAl0");
        list.add("00UlllZsqy");
        list.add("00UmlgDlTh");
        list.add("00UmlujPl8");
        list.add("00UnMEDkor");
        list.add("00Unalx1om");
        list.add("00UrkuSRJ9");
        list.add("00Urtlwlby");
        list.add("00Uv0WmVJF");
        list.add("00UwrolQil");
        list.add("00V9wwnljf");
        list.add("00VAyfllke");
        list.add("00VBSxeEqS");
        list.add("00VCHmlMlH");
        list.add("00VDWbvGIu");
        list.add("00VFk02bBi");
        list.add("00VFkkKKxU");
        list.add("00VGll0lkk");
        list.add("00VGpQnawk");
        list.add("00VMLWapQr");
        list.add("00VYJdrsBw");
        list.add("00VcTj9QcS");
        list.add("00Vd4i6xG4");
        list.add("00VdKChiWR");
        list.add("00VgSlUe6w");
        list.add("00VhA4vlvm");
        list.add("00Vj6ECKla");
        list.add("00VjlA8Hll");
        list.add("00Vjlbl0H9");
        list.add("00VkiNRmZy");
        list.add("00VlFvk0md");
        list.add("00VlYQNmFk");
        list.add("00VlbIy6lz");
        list.add("00Vll7H5Z1");
        list.add("00VlnlsusV");
        list.add("00VlrlnnlE");
        list.add("00VlsqplOP");
        list.add("00VlzMdvlA");
        list.add("00VoFMSwwo");
        list.add("00VpWsl2Ey");
        list.add("00VsUcVCuw");
        list.add("00Vuxag0dl");
        list.add("00VwljaKUl");
        list.add("00VxEQZVgP");
        list.add("00Vyiyflhm");
        list.add("00VzWOoJpx");
        list.add("00W0TQ4mME");
        list.add("00W0lIgpQl");
        list.add("00W3Dvoaqr");
        list.add("00W48cE2KV");
        list.add("00W5kQall7");
        list.add("00W8lZlZyl");
        list.add("00W9SmmkDP");
        list.add("00WAy32GK2");
        list.add("00WBVlglUE");
        list.add("00WECl6svu");
        list.add("00WFsimWas");
        list.add("00WHLSRvem");
        list.add("00WIqwtAlQ");
        list.add("00WJ5au0Ed");
        list.add("00WJp7l9p9");
        list.add("00WKlKEkLn");
        list.add("00WN3rlBlW");
        list.add("00WPkCplml");
        list.add("00WRlrieJ0");
        list.add("00WSlPBcvd");
        list.add("00WUGvl7VV");
        list.add("00WWkRxfGk");
        list.add("00WZ9okMlS");
        list.add("00WZonqs5l");
        list.add("00WalneC85");
        list.add("00WbdQwwdQ");
        list.add("00WcFTNvlm");
        list.add("00WcFlkyw7");
        list.add("00WfxkSF0p");
        list.add("00WhJsNK3W");
        list.add("00WjJxFl5t");
        list.add("00WjmLM2ll");
        list.add("00WkOrUlIT");
        list.add("00Wkvlp3ul");
        list.add("00Wl784fP8");
        list.add("00WlHYNTvE");
        list.add("00WldZ4q3G");
        list.add("00Wll0a6h6");
        list.add("00WllueUx1");
        list.add("00Wlpm9T4d");
        list.add("00Wm0x07fG");
        list.add("00Wm2lkB8w");
        list.add("00WmWxll8n");
        list.add("00Wp2WryMm");
        list.add("00WpkY1qLi");
        list.add("00Wplsxcez");
        list.add("00WrlZspqm");
        list.add("00WspBNjgJ");
        list.add("00WuYoWGkl");
        list.add("00Wulkklgc");
        list.add("00WulqDyzZ");
        list.add("00WvApJxlJ");
        list.add("00Wx6EMdZ3");
        list.add("00WxIlAIYk");
        list.add("00Wxk2Rvim");
        list.add("00Wxrk4llw");
        list.add("00X0AlOjdY");
        list.add("00X0pFySll");
        list.add("00X1lakylp");
        list.add("00X4gSqByI");
        list.add("00X5ckEviX");
        list.add("00X7HAmkYl");
        list.add("00X8CE13Xn");
        list.add("00X9ClQlvq");
        list.add("00X9uVdx0l");
        list.add("00XA7TPqzu");
        list.add("00XB0zaQo4");
        list.add("00XCn3ujVg");
        list.add("00XCrltlod");
        list.add("00XErx0hdD");
        list.add("00XFlUHGut");
        list.add("00XFlzpmvk");
        list.add("00XJammeQ8");
        list.add("00XLlklp6Y");
        list.add("00XLuzllfq");
        list.add("00XN6lzQ0l");
        list.add("00XQlAlNkl");
        list.add("00XSllyA9Z");
        list.add("00XSuP4mk8");
        list.add("00XSuvlZ0z");
        list.add("00XTiusf7k");
        list.add("00XU4GFlCX");
        list.add("00XUqSlnvr");
        list.add("00XVeCSgSp");
        list.add("00Xb5Gf8lB");
        list.add("00XbyBoQlB");
        list.add("00XcNlmeJp");
        list.add("00XfleElkl");
        list.add("00Xhtg9qC2");
        list.add("00Xi2kzUvC");
        list.add("00XjO7llbU");
        list.add("00XkkS2Isn");
        list.add("00XkllMOuO");
        list.add("00XklmwlBL");

        for (int i =0; i<list.size() ;i++){
              System.out.println(  HashAlgorithms.additiveHash(list.get(i), 4));
            //System.out.println(  HashAlgorithms.rotatingHash(list.get(i), 6));

           // System.out.println(new String(list.get(i)).hashCode());
          }
      //  ConcurrentHashMap a = new ConcurrentHashMap();

//        String str = new String().hashCode();
//        str.hashCode();
    }

//    private final int count =0;
//
//    private int hash =0;
//
//    public int hashCode() {
//        int h = hash;
//        int len = count;
//        if (h == 0 && len > 0) {
//            int off = offset;
//            char val[] = value;
//
//            for (int i = 0; i < len; i++) {
//                h = 31*h + val[off++];
//            }
//            hash = h;
//        }
//        return h;
//    }

    // 替代：
    // 使用：hash = (hash ^ (hash>>10) ^ (hash>>20)) & mask;
    // 替代：hash %= prime;

    /**//**
     * MASK值，随便找一个值，最好是质数
     */
    static int M_MASK = 0x8765fed1;
    /**//**
     * 一次一个hash
     * @param key 输入字符串
     * @return 输出hash值
     */
    public static int oneByOneHash(String key)
    {
        int   hash, i;
        for (hash=0, i=0; i<key.length(); ++i)
        {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        //   return (hash & M_MASK);
        return hash;
    }

    /**//**
     * Bernstein's hash
     * @param key 输入字节数组
     * @return 结果hash
     */
    public static int bernstein(String key)
    {
        int hash = 0;
        int i;
        for (i=0; i<key.length(); ++i) hash = 33*hash + key.charAt(i);
        return hash;
    }

    //
    /**///// Pearson's Hash
    // char pearson(char[]key, ub4 len, char tab[256])
    // {
    //   char hash;
    //   ub4 i;
    //   for (hash=len, i=0; i<len; ++i)
    //     hash=tab[hash^key[i]];
    //   return (hash);
    // }

    /**///// CRC Hashing，计算crc,具体代码见其他
    // ub4 crc(char *key, ub4 len, ub4 mask, ub4 tab[256])
    // {
    //   ub4 hash, i;
    //   for (hash=len, i=0; i<len; ++i)
    //     hash = (hash >> 8) ^ tab[(hash & 0xff) ^ key[i]];
    //   return (hash & mask);
    // }

    /**//**
     * Universal Hashing
     */
    public static int universal(char[]key, int mask, int[] tab)
    {
        int hash = key.length, i, len = key.length;
        for (i=0; i<(len<<3); i+=8)
        {
            char k = key[i>>3];
            if ((k&0x01) == 0) hash ^= tab[i+0];
            if ((k&0x02) == 0) hash ^= tab[i+1];
            if ((k&0x04) == 0) hash ^= tab[i+2];
            if ((k&0x08) == 0) hash ^= tab[i+3];
            if ((k&0x10) == 0) hash ^= tab[i+4];
            if ((k&0x20) == 0) hash ^= tab[i+5];
            if ((k&0x40) == 0) hash ^= tab[i+6];
            if ((k&0x80) == 0) hash ^= tab[i+7];
        }
        return (hash & mask);
    }

    /**//**
     * Zobrist Hashing
     */
    public static int zobrist( char[] key,int mask, int[][] tab)
    {
        int hash, i;
        for (hash=key.length, i=0; i<key.length; ++i)
            hash ^= tab[i][key[i]];
        return (hash & mask);
    }

    // LOOKUP3
    // 见Bob Jenkins(3).c文件

    // 32位FNV算法
    static int M_SHIFT = 0;
    /**//**
     * 32位的FNV算法
     * @param data 数组
     * @return int值
     */
    public static int FNVHash(byte[] data)
    {
        int hash = (int)2166136261L;
        for(byte b : data)
            hash = (hash * 16777619) ^ b;
        if (M_SHIFT == 0)
            return hash;
        return (hash ^ (hash >> M_SHIFT)) & M_MASK;
    }
    /**//**
     * 改进的32位FNV算法1
     * @param data 数组
     * @return int值
     */
    public static int FNVHash1(byte[] data)
    {
        final int p = 16777619;
        int hash = (int)2166136261L;
        for(byte b:data)
            hash = (hash ^ b) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }
    /**//**
     * 改进的32位FNV算法1
     * @param data 字符串
     * @return int值
     */
    public static int FNVHash1(String data)
    {
        final int p = 16777619;
        int hash = (int)2166136261L;
        for(int i=0;i<data.length();i++)
            hash = (hash ^ data.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }

    /**//**
     * Thomas Wang的算法，整数hash
     */
    public static int intHash(int key)
    {
        key += ~(key << 15);
        key ^= (key >>> 10);
        key += (key << 3);
        key ^= (key >>> 6);
        key += ~(key << 11);
        key ^= (key >>> 16);
        return key;
    }
    /**//**
     * RS算法hash
     * @param str 字符串
     */
    public static int RSHash(String str)
    {
        int b    = 378551;
        int a    = 63689;
        int hash = 0;

        for(int i = 0; i < str.length(); i++)
        {
            hash = hash * a + str.charAt(i);
            a    = a * b;
        }

        return (hash & 0x7FFFFFFF);
    }
    /**//* End Of RS Hash Function */

    /**//**
     * JS算法
     */
    public static int JSHash(String str)
    {
        int hash = 1315423911;

        for(int i = 0; i < str.length(); i++)
        {
            hash ^= ((hash << 5) + str.charAt(i) + (hash >> 2));
        }

        return (hash & 0x7FFFFFFF);
    }
    /**//* End Of JS Hash Function */

    /**//**
     * PJW算法
     */
    public static int PJWHash(String str)
    {
        int BitsInUnsignedInt = 32;
        int ThreeQuarters     = (BitsInUnsignedInt * 3) / 4;
        int OneEighth         = BitsInUnsignedInt / 8;
        int HighBits          = 0xFFFFFFFF << (BitsInUnsignedInt - OneEighth);
        int hash              = 0;
        int test              = 0;

        for(int i = 0; i < str.length();i++)
        {
            hash = (hash << OneEighth) + str.charAt(i);

            if((test = hash & HighBits) != 0)
            {
                hash = (( hash ^ (test >> ThreeQuarters)) & (~HighBits));
            }
        }

        return (hash & 0x7FFFFFFF);
    }
    /**//* End Of P. J. Weinberger Hash Function */

    /**//**
     * ELF算法
     */
    public static int ELFHash(String str)
    {
        int hash = 0;
        int x    = 0;

        for(int i = 0; i < str.length(); i++)
        {
            hash = (hash << 4) + str.charAt(i);
            if((x = (int)(hash & 0xF0000000L)) != 0)
            {
                hash ^= (x >> 24);
                hash &= ~x;
            }
        }

        return (hash & 0x7FFFFFFF);
    }
    /**//* End Of ELF Hash Function */

    /**//**
     * BKDR算法
     */
    public static int BKDRHash(String str)
    {
        int seed = 131; // 31 131 1313 13131 131313 etc..
        int hash = 0;

        for(int i = 0; i < str.length(); i++)
        {
            hash = (hash * seed) + str.charAt(i);
        }

        return (hash & 0x7FFFFFFF);
    }
    /**//* End Of BKDR Hash Function */

    /**//**
     * SDBM算法
     */
    public static int SDBMHash(String str)
    {
        int hash = 0;

        for(int i = 0; i < str.length(); i++)
        {
            hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }

        return (hash & 0x7FFFFFFF);
    }
    /**//* End Of SDBM Hash Function */

    /**//**
     * DJB算法
     */
    public static int DJBHash(String str)
    {
        int hash = 5381;

        for(int i = 0; i < str.length(); i++)
        {
            hash = ((hash << 5) + hash) + str.charAt(i);
        }

        return (hash & 0x7FFFFFFF);
    }
    /**//* End Of DJB Hash Function */

    /**//**
     * DEK算法
     */
    public static int DEKHash(String str)
    {
        int hash = str.length();

        for(int i = 0; i < str.length(); i++)
        {
            hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
        }

        return (hash & 0x7FFFFFFF);
    }
    /**//* End Of DEK Hash Function */

    /**//**
     * AP算法
     */
    public static int APHash(String str)
    {
        int hash = 0;

        for(int i = 0; i < str.length(); i++)
        {
            hash ^= ((i & 1) == 0) ? ( (hash << 7) ^ str.charAt(i) ^ (hash >> 3)) :
                    (~((hash << 11) ^ str.charAt(i) ^ (hash >> 5)));
        }

        //       return (hash & 0x7FFFFFFF);
        return hash;
    }
    /**//* End Of AP Hash Function */

    /**//**
     * JAVA自己带的算法
     */
    public static int java(String str)
    {
        int h = 0;
        int off = 0;
        int len = str.length();
        for (int i = 0; i < len; i++)
        {
            h = 31 * h + str.charAt(off++);
        }
        return h;
    }

    /**//**
     * 混合hash算法，输出64位的值
     */
    public static long mixHash(String str)
    {
        long hash = str.hashCode();
        hash <<= 32;
        hash |= FNVHash1(str);
        return hash;
    }
}
