package com.nleaves.study.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * String Utils
 */
public class StrUtils {

    /**
     * is null or its length is 0 or it is made by space
     * <p/>
     * <pre>
     * isBlank(null) = true;
     * isBlank(&quot;&quot;) = true;
     * isBlank(&quot;  &quot;) = true;
     * isBlank(&quot;a&quot;) = false;
     * isBlank(&quot;a &quot;) = false;
     * isBlank(&quot; a&quot;) = false;
     * isBlank(&quot;a b&quot;) = false;
     * </pre>
     *
     * @param str
     * @return if string is null or its size is 0 or it is made by space, return
     * true, else return false.
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * is null or its length is 0
     * <p/>
     * <pre>
     * isEmpty(null) = true;
     * isEmpty(&quot;&quot;) = true;
     * isEmpty(&quot;  &quot;) = false;
     * </pre>
     *
     * @param str
     * @return if string is null or its size is 0, return true, else return
     * false.
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * compare two string
     *
     * @param actual
     * @param expected
     * @return
     * @see ObjectUtils#isEquals(Object, Object)
     */
    // public static boolean isEquals(String actual, String expected) {
    // return ObjectUtils.isEquals(actual, expected);
    // }

    /**
     * null string to empty string
     * <p/>
     * <pre>
     * nullStrToEmpty(null) = &quot;&quot;;
     * nullStrToEmpty(&quot;&quot;) = &quot;&quot;;
     * nullStrToEmpty(&quot;aa&quot;) = &quot;aa&quot;;
     * </pre>
     *
     * @param str
     * @return
     */
    public static String nullStrToEmpty(String str) {
        return (str == null ? "" : str);
    }

    /**
     * capitalize first letter
     * <p/>
     * <pre>
     * capitalizeFirstLetter(null)     =   null;
     * capitalizeFirstLetter("")       =   "";
     * capitalizeFirstLetter("2ab")    =   "2ab"
     * capitalizeFirstLetter("a")      =   "A"
     * capitalizeFirstLetter("ab")     =   "Ab"
     * capitalizeFirstLetter("Abc")    =   "Abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str
                : new StringBuilder(str.length())
                .append(Character.toUpperCase(c))
                .append(str.substring(1)).toString();
    }

    /**
     * encoded in utf-8
     * <p/>
     * <pre>
     * utf8Encode(null)        =   null
     * utf8Encode("")          =   "";
     * utf8Encode("aa")        =   "aa";
     * utf8Encode("啊啊啊啊")   = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
     * </pre>
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException if an error occurs
     */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(
                        "UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * encoded in utf-8, if exception, return defultReturn
     *
     * @param str
     * @param defultReturn
     * @return
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }

    /**
     * get innerHtml from href
     * <p/>
     * <pre>
     * getHrefInnerHtml(null)                                  = ""
     * getHrefInnerHtml("")                                    = ""
     * getHrefInnerHtml("mp3")                                 = "mp3";
     * getHrefInnerHtml("&lt;a innerHtml&lt;/a&gt;")                    = "&lt;a innerHtml&lt;/a&gt;";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
     * getHrefInnerHtml("&lt;a&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com"&gt;innerHtml&lt;/a&gt;")               = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com" title="baidu"&gt;innerHtml&lt;/a&gt;") = "innerHtml";
     * getHrefInnerHtml("   &lt;a&gt;innerHtml&lt;/a&gt;  ")                           = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                      = "innerHtml";
     * getHrefInnerHtml("jack&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                  = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml1&lt;/a&gt;&lt;a&gt;innerHtml2&lt;/a&gt;")        = "innerHtml2";
     * </pre>
     *
     * @param href
     * @return <ul>
     * <li>if href is null, return ""</li>
     * <li>if not match regx, return source</li>
     * <li>return the last string that match regx</li>
     * </ul>
     */
    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        }

        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
        Pattern hrefPattern = Pattern
                .compile(hrefReg, Pattern.CASE_INSENSITIVE);
        Matcher hrefMatcher = hrefPattern.matcher(href);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return href;
    }

    /**
     * process special char in html
     * <p/>
     * <pre>
     * htmlEscapeCharsToString(null) = null;
     * htmlEscapeCharsToString("") = "";
     * htmlEscapeCharsToString("mp3") = "mp3";
     * htmlEscapeCharsToString("mp3&lt;") = "mp3<";
     * htmlEscapeCharsToString("mp3&gt;") = "mp3\>";
     * htmlEscapeCharsToString("mp3&amp;mp4") = "mp3&mp4";
     * htmlEscapeCharsToString("mp3&quot;mp4") = "mp3\"mp4";
     * htmlEscapeCharsToString("mp3&lt;&gt;&amp;&quot;mp4") = "mp3\<\>&\"mp4";
     * </pre>
     *
     * @param source
     * @return
     */
    public static String htmlEscapeCharsToString(String source) {
        return StrUtils.isEmpty(source) ? source : source
                .replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    public static String formatRateString(String str) {

        Double d1 = Double.parseDouble(str);

        NumberFormat ddf1 = NumberFormat.getNumberInstance();
        ddf1.setMaximumFractionDigits(1);
        return ddf1.format(d1);
    }


    public static String formatAccountString(String str) {

        Double d1 = Double.parseDouble(str) / 10000;

        NumberFormat ddf1 = NumberFormat.getNumberInstance();
        ddf1.setMaximumFractionDigits(1);
        return ddf1.format(d1);
    }


    /**
     * transform half width char to full width char
     * <p/>
     * <pre>
     * fullWidthToHalfWidth(null) = null;
     * fullWidthToHalfWidth("") = "";
     * fullWidthToHalfWidth(new String(new char[] {12288})) = " ";
     * fullWidthToHalfWidth("！＂＃＄％＆) = "!\"#$%&";
     * </pre>
     *
     * @param s
     * @return
     */
    public static String fullWidthToHalfWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char) (source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * transform full width char to half width char
     * <p/>
     * <pre>
     * halfWidthToFullWidth(null) = null;
     * halfWidthToFullWidth("") = "";
     * halfWidthToFullWidth(" ") = new String(new char[] {12288});
     * halfWidthToFullWidth("!\"#$%&) = "！＂＃＄％＆";
     * </pre>
     *
     * @param s
     * @return
     */
    public static String halfWidthToFullWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char) 12288;
                // } else if (source[i] == '.') {
                // source[i] = (char)12290;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char) (source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }


    public static String splitString(String str) {
        return splitString(str, 15);
    }

    public static String splitString(String str, int num) {
        if (StrUtils.isEmpty(str)) {
            return str;
        }
        if (str.length() > num) {
            String subString = str.substring(0, num);
            return subString+"... ";
        } else {
            return str;
        }
    }

    public static String getNoEmptyString(String str) {

        if (isEmpty(str)) {
            return "";
        } else {
            return "\"" + str + "\"";
        }
    }

    public static boolean isLastAtString(CharSequence str, int at) {
        if (isEmpty(str.toString())) {
            return false;
        } else {
            if (at > str.length()) {
                at = str.length();
            }
            return at > 0 && str.subSequence(at - 1, at).toString().equals("@");
        }
    }

    public static boolean isLastTagString(CharSequence str, int at) {
        if (isEmpty(str.toString())) {
            return false;
        } else {
            if (at > str.length()) {
                at = str.length();
            }
            return at > 0 && str.subSequence(at - 1, at).toString().equalsIgnoreCase("#");
        }
    }

    public static String getLastString(String str) {
        return getLastString(str, 10);
    }

    public static String getLastStringM(String str) {
        return getLastStringM(str, 10);
    }

    public static String getLastString(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len);
        }
        return str;
    }

    public static String getLastStringM(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len) + "...";
        }
        return str;
    }

    public static String getContentStr(String str) {
        if (str.length() > 140) {
            return str.substring(0, 140) + "...";
        }
        return str;
    }


    public static String getNumStr(int num) {
        if (num < 10000) {
            return num + "";
        } else {
            return String.format("%.1f", num / 10000f) + " W";
        }
    }

    public static int getSubstringLength(String inputStr) {
        int orignLen = inputStr.length();
        int resultLen = 0;
        String temp = null;
        for (int i = 0; i < orignLen; i++) {
            temp = inputStr.substring(i, i + 1);
            try {// 3 bytes to indicate chinese word,1 byte to indicate english
                // word ,in utf-8 encode
                if (temp.getBytes("utf-8").length == 3) {
                    resultLen += 2;
                } else {
                    resultLen++;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return resultLen;
    }


    //TODO - complete JavaDoc

    /**
     * Constant representing the empty string, equal to &quot;&quot;
     */
    public static final String EMPTY_STRING = "";

    /**
     * Constant representing the default delimiter character (comma), equal to <code>','</code>
     */
    public static final char DEFAULT_DELIMITER_CHAR = ',';

    /**
     * Constant representing the default quote character (double quote), equal to '&quot;'</code>
     */
    public static final char DEFAULT_QUOTE_CHAR = '"';

    /**
     * Check whether the given String has actual text.
     * More specifically, returns <code>true</code> if the string not <code>null</code>,
     * its length is greater than 0, and it contains at least one non-whitespace character.
     * <p/>
     * <code>StringUtils.hasText(null) == false<br/>
     * StringUtils.hasText("") == false<br/>
     * StringUtils.hasText(" ") == false<br/>
     * StringUtils.hasText("12345") == true<br/>
     * StringUtils.hasText(" 12345 ") == true</code>
     * <p/>
     * <p>Copied from the Spring Framework while retaining all license, copyright and author information.
     *
     * @param str the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not <code>null</code>, its length is
     * greater than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
    public static boolean hasText(String str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check that the given String is neither <code>null</code> nor of length 0.
     * Note: Will return <code>true</code> for a String that purely consists of whitespace.
     * <p/>
     * <code>StringUtils.hasLength(null) == false<br/>
     * StringUtils.hasLength("") == false<br/>
     * StringUtils.hasLength(" ") == true<br/>
     * StringUtils.hasLength("Hello") == true</code>
     * <p/>
     * Copied from the Spring Framework while retaining all license, copyright and author information.
     *
     * @param str the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not null and has length
     * @see #hasText(String)
     */
    public static boolean hasLength(String str) {
        return (str != null && str.length() > 0);
    }


    /**
     * Test if the given String starts with the specified prefix,
     * ignoring upper/lower case.
     * <p/>
     * <p>Copied from the Spring Framework while retaining all license, copyright and author information.
     *
     * @param str    the String to check
     * @param prefix the prefix to look for
     * @return <code>true</code> starts with the specified prefix (ignoring case), <code>false</code> if it does not.
     * @see String#startsWith
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str == null || prefix == null) {
            return false;
        }
        if (str.startsWith(prefix)) {
            return true;
        }
        if (str.length() < prefix.length()) {
            return false;
        }
        String lcStr = str.substring(0, prefix.length()).toLowerCase();
        String lcPrefix = prefix.toLowerCase();
        return lcStr.equals(lcPrefix);
    }

    /**
     * Returns a 'cleaned' representation of the specified argument.  'Cleaned' is defined as the following:
     * <p/>
     * <ol>
     * <li>If the specified <code>String</code> is <code>null</code>, return <code>null</code></li>
     * <li>If not <code>null</code>, {@link String#trim() trim()} it.</li>
     * <li>If the trimmed string is equal to the empty String (i.e. &quot;&quot;), return <code>null</code></li>
     * <li>If the trimmed string is not the empty string, return the trimmed version</li>.
     * </ol>
     * <p/>
     * Therefore this method always ensures that any given string has trimmed text, and if it doesn't, <code>null</code>
     * is returned.
     *
     * @param in the input String to clean.
     * @return a populated-but-trimmed String or <code>null</code> otherwise
     */
    public static String clean(String in) {
        String out = in;

        if (in != null) {
            out = in.trim();
            if (out.equals(EMPTY_STRING)) {
                out = null;
            }
        }

        return out;
    }

    /**
     * Returns the specified array as a comma-delimited (',') string.
     *
     * @param array the array whose contents will be converted to a string.
     * @return the array's contents as a comma-delimited (',') string.
     * @since 1.0
     */
    public static String toString(Object[] array) {
        return toDelimitedString(array, ",");
    }

    /**
     * Returns the array's contents as a string, with each element delimited by the specified
     * {@code delimiter} argument.  Useful for {@code toString()} implementations and log messages.
     *
     * @param array     the array whose contents will be converted to a string
     * @param delimiter the delimiter to use between each element
     * @return a single string, delimited by the specified {@code delimiter}.
     * @since 1.0
     */
    public static String toDelimitedString(Object[] array, String delimiter) {
        if (array == null || array.length == 0) {
            return EMPTY_STRING;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(delimiter);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }

    /**
     * Returns the collection's contents as a string, with each element delimited by the specified
     * {@code delimiter} argument.  Useful for {@code toString()} implementations and log messages.
     *
     * @param c         the collection whose contents will be converted to a string
     * @param delimiter the delimiter to use between each element
     * @return a single string, delimited by the specified {@code delimiter}.
     * @since 1.2
     */
    public static String toDelimitedString(Collection c, String delimiter) {
        if (c == null || c.isEmpty()) {
            return EMPTY_STRING;
        }
        return join(c.iterator(), delimiter);
    }

    /**
     * Tokenize the given String into a String array via a StringTokenizer.
     * Trims tokens and omits empty tokens.
     * <p>The given delimiters string is supposed to consist of any number of
     * delimiter characters. Each of those characters can be used to separate
     * tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using <code>delimitedListToStringArray</code>
     * <p/>
     * <p>Copied from the Spring Framework while retaining all license, copyright and author information.
     *
     * @param str        the String to tokenize
     * @param delimiters the delimiter characters, assembled as String
     *                   (each of those characters is individually considered as delimiter).
     * @return an array of the tokens
     * @see StringTokenizer
     * @see String#trim()
     */
    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    /**
     * Tokenize the given String into a String array via a StringTokenizer.
     * <p>The given delimiters string is supposed to consist of any number of
     * delimiter characters. Each of those characters can be used to separate
     * tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using <code>delimitedListToStringArray</code>
     * <p/>
     * <p>Copied from the Spring Framework while retaining all license, copyright and author information.
     *
     * @param str               the String to tokenize
     * @param delimiters        the delimiter characters, assembled as String
     *                          (each of those characters is individually considered as delimiter)
     * @param trimTokens        trim the tokens via String's <code>trim</code>
     * @param ignoreEmptyTokens omit empty tokens from the result array
     *                          (only applies to tokens that are empty after trimming; StringTokenizer
     *                          will not consider subsequent delimiters as token in the first place).
     * @return an array of the tokens (<code>null</code> if the input String
     * was <code>null</code>)
     * @see StringTokenizer
     * @see String#trim()
     */
    @SuppressWarnings({"unchecked"})
    public static String[] tokenizeToStringArray(
            String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

        if (str == null) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, delimiters);
        List tokens = new ArrayList();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0) {
                tokens.add(token);
            }
        }
        return toStringArray(tokens);
    }

    /**
     * Copy the given Collection into a String array.
     * The Collection must contain String elements only.
     * <p/>
     * <p>Copied from the Spring Framework while retaining all license, copyright and author information.
     *
     * @param collection the Collection to copy
     * @return the String array (<code>null</code> if the passed-in
     * Collection was <code>null</code>)
     */
    @SuppressWarnings({"unchecked"})
    public static String[] toStringArray(Collection collection) {
        if (collection == null) {
            return null;
        }
        return (String[]) collection.toArray(new String[collection.size()]);
    }


    public static String[] split(String line) {
        return split(line, DEFAULT_DELIMITER_CHAR);
    }

    public static String[] split(String line, char delimiter) {
        return split(line, delimiter, DEFAULT_QUOTE_CHAR);
    }

    public static String[] split(String line, char delimiter, char quoteChar) {
        return split(line, delimiter, quoteChar, quoteChar);
    }

    public static String[] split(String line, char delimiter, char beginQuoteChar, char endQuoteChar) {
        return split(line, delimiter, beginQuoteChar, endQuoteChar, false, true);
    }

    /**
     * Splits the specified delimited String into tokens, supporting quoted tokens so that quoted strings themselves
     * won't be tokenized.
     * <p/>
     * This method's implementation is very loosely based (with significant modifications) on
     * <a href="http://blogs.bytecode.com.au/glen">Glen Smith</a>'s open-source
     * <a href="http://opencsv.svn.sourceforge.net/viewvc/opencsv/trunk/src/au/com/bytecode/opencsv/CSVReader.java?&view=markup">CSVReader.java</a>
     * file.
     * <p/>
     * That file is Apache 2.0 licensed as well, making Glen's code a great starting point for us to modify to
     * our needs.
     *
     * @param aLine          the String to parse
     * @param delimiter      the delimiter by which the <tt>line</tt> argument is to be split
     * @param beginQuoteChar the character signifying the start of quoted text (so the quoted text will not be split)
     * @param endQuoteChar   the character signifying the end of quoted text
     * @param retainQuotes   if the quotes themselves should be retained when constructing the corresponding token
     * @param trimTokens     if leading and trailing whitespace should be trimmed from discovered tokens.
     * @return the tokens discovered from parsing the given delimited <tt>line</tt>.
     */
    public static String[] split(String aLine, char delimiter, char beginQuoteChar, char endQuoteChar,
                                 boolean retainQuotes, boolean trimTokens) {
        String line = clean(aLine);
        if (line == null) {
            return null;
        }

        List<String> tokens = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);
            if (c == beginQuoteChar) {
                // this gets complex... the quote may end a quoted block, or escape another quote.
                // do a 1-char lookahead:
                if (inQuotes  // we are in quotes, therefore there can be escaped quotes in here.
                        && line.length() > (i + 1)  // there is indeed another character to check.
                        && line.charAt(i + 1) == beginQuoteChar) { // ..and that char. is a quote also.
                    // we have two quote chars in a row == one quote char, so consume them both and
                    // put one on the token. we do *not* exit the quoted text.
                    sb.append(line.charAt(i + 1));
                    i++;
                } else {
                    inQuotes = !inQuotes;
                    if (retainQuotes) {
                        sb.append(c);
                    }
                }
            } else if (c == endQuoteChar) {
                inQuotes = !inQuotes;
                if (retainQuotes) {
                    sb.append(c);
                }
            } else if (c == delimiter && !inQuotes) {
                String s = sb.toString();
                if (trimTokens) {
                    s = s.trim();
                }
                tokens.add(s);
                sb = new StringBuilder(); // start work on next token
            } else {
                sb.append(c);
            }
        }
        String s = sb.toString();
        if (trimTokens) {
            s = s.trim();
        }
        tokens.add(s);
        return tokens.toArray(new String[tokens.size()]);
    }

    /**
     * Joins the elements of the provided {@code Iterator} into
     * a single String containing the provided elements.</p>
     * <p/>
     * No delimiter is added before or after the list.
     * A {@code null} separator is the same as an empty String ("").</p>
     * <p/>
     * Copied from Commons Lang, version 3 (r1138702).</p>
     *
     * @param iterator  the {@code Iterator} of values to join together, may be null
     * @param separator the separator character to use, null treated as ""
     * @return the joined String, {@code null} if null iterator input
     * @since 1.2
     */
    public static String join(Iterator<?> iterator, String separator) {
        final String empty = "";

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return empty;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return first == null ? empty : first.toString();
        }

        // two or more elements
        StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    /**
     * Splits the {@code delimited} string (delimited by the specified {@code separator} character) and returns the
     * delimited values as a {@code Set}.
     * <p/>
     * If either argument is {@code null}, this method returns {@code null}.
     *
     * @param delimited the string to split
     * @param separator the character that delineates individual tokens to split
     * @return the delimited values as a {@code Set}.
     * @since 1.2
     */


    /**
     * Returns the input argument, but ensures the first character is capitalized (if possible).
     *
     * @param in the string to uppercase the first character.
     * @return the input argument, but with the first character capitalized (if possible).
     * @since 1.2
     */
    public static String uppercaseFirstChar(String in) {
        if (in == null || in.length() == 0) {
            return in;
        }
        int length = in.length();
        StringBuilder sb = new StringBuilder(length);

        sb.append(Character.toUpperCase(in.charAt(0)));
        if (length > 1) {
            String remaining = in.substring(1);
            sb.append(remaining);
        }
        return sb.toString();
    }

}
