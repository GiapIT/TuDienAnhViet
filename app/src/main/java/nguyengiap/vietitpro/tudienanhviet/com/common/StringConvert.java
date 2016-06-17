package nguyengiap.vietitpro.tudienanhviet.com.common;

import android.text.TextUtils;

import java.util.ArrayList;

import nguyengiap.vietitpro.tudienanhviet.com.model.EngVietDictSearch;


public class StringConvert {

    //phát âm >> /a/
    public static String itemcontent(String str) {
        String data = "";
        String item = "";
        if (str.contains("#")) {
            data = str.substring(0, str.indexOf("#"));
            if (data.length() > 0) {
                if (data.contains("[")) {
                    data.replace("[", " \\/ ");
                }
                if (data.contains("]")) {
                    data.replace("]", " \\/ ");
                }

                item = data;

            } else {
                item = "";
            }
        }
        return item;

    }

    public static String content(String str) {
        String data;
        String content = "";
        if (str.contains("#")) {
            if (str.contains("#*")) {
                str = str.substring(str.indexOf("*") + 1);
            } else if (str.contains("#-")) {
                str = str.substring(str.indexOf("-") + 1);
            }
            data = str.trim();
            data = data.substring(0, 1).toUpperCase().trim() + data.substring(1, data.length()).trim();

            data = data.replace("|-", " : ");
            if (data.contains("|=")) {
                String[] arrayOfString = data.split("\\|=");
                content = arrayOfString[0];
                for (int i = 0; i < arrayOfString.length; i++) {
                    data = arrayOfString[i];
                    if (data.contains("|+")) {
                        data = data.substring(data.indexOf("+") + 1);
                        content += data + ",";
                    }
                }
                if (content.contains("|*")) {
                    content = content.replace("|*", ";");
                }
            } else if (data.contains("|*")) {
                String[] arrayOfString = data.split(":");
                content = arrayOfString[0] + " : ";
                for (int i = 0; i < arrayOfString.length; i++) {
                    data = arrayOfString[i];
                    if (data.contains("|*")) {
                        data = data.replace("|*", ";");
                    }
                    content += data + ",";
                }
            } else {
                content = data;
            }
        }/*else {

        }*/
        return content;
    }

    public static ArrayList listEngVietDictSearchEN(String str) {

        ArrayList list = new ArrayList();
        String data = "";
        String content = "";
        String subcontent = "";

        if (str.contains("=")) {
            str = str.replace("=", "$");
        }
        if (str.contains("#")) {
            if (str.contains("#*")) {
                str = str.substring(str.indexOf("*"));
            } else if (str.contains("#- ")) {
                str = str.substring(str.indexOf("- ") + 1);
            }
            if (str.contains("#- ")) {
                str = str.substring(str.indexOf("- ") + 1);
            }
            data = str.trim();
            if (data.contains("|")) {
                String[] arrayOfString = data.split("\\|");
                for (int i = 0; i < arrayOfString.length; i++) {
                    data = arrayOfString[i];

                    if (!(data.contains("*") || data.contains("@")) && i > 0) {
                        if (data.contains("- ")) {
                            data = data.replace("- ", "").trim();
                            data = data.substring(0, 1).toUpperCase().trim() + data.substring(1, data.length()).trim();
                            data = "<p><h7> ☞ <font color=\"#000000\" >  &nbsp;" + data + "</h7></p>";
                        }
                        if (data.contains("$")) {
                            data = data.replace("$", "• ");
                            String[] arr = data.split(" ");
                            data = arr[0];
                            for (int j = 0; j < arr.length; j++) {
                                if (j > 0) {
                                    String s = "{" + arr[j] + "}";
                                    data += s + " ";
                                }
                            }
                            data = "&nbsp;&nbsp;&nbsp;" + data + "<br>";
                        }
                        if (data.contains("+")) {
                            data = "&nbsp;<font color=\"#727272\" ><center> &nbsp;&nbsp;" + data.replace("+", "  ") + "</center><br>";
                        }
                        subcontent += data;
                    } else if (data.contains("*") || data.contains("@")) {
                        subcontent = subcontent + "==";
                        if (data.contains("*")) {
                            data = data.substring(data.indexOf("*") + 1).trim();
                        }
                        if (data.contains("@")) {
                            data = data.substring(data.indexOf("@") + 1).trim();
                        }
                        content += data.substring(0, 1).toUpperCase().trim() + data.substring(1, data.length()).trim() + "==";
                    } else {
                        subcontent = subcontent + "==";
                        content += data + "==";
                    }
                }
                String[] arrContent = content.split("==");
                String[] arrSubContent = subcontent.substring(subcontent.indexOf("==") + 2).split("==");
                for (int i = 0; i < arrContent.length; i++) {
                    String a = arrContent[i];
                    String b = arrSubContent[i];
                    EngVietDictSearch item = new EngVietDictSearch(a, b);
                    list.add(item);
                }
            } else {
                if (data.contains("*")) {
                    data = data.replace("*", "☞");
                    data = "<p><h7> ☞ <font color=\"#000000\" >  &nbsp;" + data + "</h7></p>";
                    list.add(new EngVietDictSearch("", data));
                } else if (data.contains("-")) {
                    data = data.substring(data.indexOf("-"), data.length());
                    data = data.replace("-", "☞");
                    data = "<p><h7> ☞ <font color=\"#000000\" >  &nbsp;" + data + "</h7></p>";
                    list.add(new EngVietDictSearch("", data));
                } else {
                    data = "☞" + data;
                    data = "<p><h7> ☞ <font color=\"#000000\" >  &nbsp;" + data + "</h7></p>";
                    list.add(new EngVietDictSearch("", data));
                }
            }
        } else {
            list.add(new EngVietDictSearch("", data));
        }
        return list;
    }

    public static ArrayList listEngVietDictSearchVI(String str) {

        ArrayList list = new ArrayList();
        String data = "";
        String content = "";
        String subcontent = "";

        //////////////////////
        if (str.contains("#")) {
            if (str.contains("#*")) {
                str = str.substring(str.indexOf("*"));
            } else if (str.contains("#-")) {
                str = str.substring(str.indexOf("-"));
            }
            data = str.trim();

            if (data.contains("|")) {
                if (data.contains("*") || data.contains("@")) {
                    String[] arrayOfString = data.split("\\|");
                    for (int i = 0; i < arrayOfString.length; i++) {
                        if (arrayOfString[0].contains("*") || arrayOfString[0].contains("@")) {
                            data = arrayOfString[i];
                            if (!(data.contains("*") || data.contains("@"))) {
                                if (data.contains("- ")) {
                                    data = data.replace("- ", "");
                                    subcontent += "<p><h7> ☞ <font color=\"#000000\" >  &nbsp;" + data + "</h7></p>";
                                }
                                if (data.contains("=")) {
                                    data = data.replace("=", "• ");
                                    data = data.trim();
                                    subcontent += "<font color=\"#727272\" > &nbsp;&nbsp;" + data + "<br>";
                                }
                                if (!(data.contains("-") || (data.contains("=") || data.contains("• ")))) {
                                    data = data.substring(0, 1).toUpperCase().trim() + data.substring(1, data.length()).trim();
                                    subcontent += "<p><h7> ☞ <font color=\"#000000\" >  &nbsp;" + data + "</h7></p>";
                                }
                            } else {
                                subcontent = subcontent + "==";

                                content += data.substring(data.indexOf("*") + 1) + "==";
                            }
                        } else {
                            data = arrayOfString[i];
                            content = content + "==";
                            if (!(data.contains("*") || data.contains("@"))) {
                                if (data.contains("- ")) {
                                    data = data.replace("- ", "");
                                }
                                if (data.contains("=")) {
                                    data = data.replace("=", "• ");
                                    data = data.trim();
                                    subcontent += "<font color=\"#727272\" > &nbsp;&nbsp;" + data + "<br>";
                                }
                                if (!(data.contains("-") || (data.contains("=") || data.contains("• ")))) {
                                    data = data.substring(0, 1).toUpperCase().trim() + data.substring(1, data.length()).trim();
                                    subcontent += "<p><h7> ☞ <font color=\"#000000\" >  &nbsp;" + data + "</h7></p>";
                                }
                            } else {
                                subcontent = subcontent + "==";
                                content += data + "==";
                            }
                        }
                    }

                    String[] arrContent = content.split("==");
                    String[] arrSubContent = subcontent.substring(subcontent.indexOf("==") + 2).split("==");
                    for (int i = 0; i < arrContent.length; i++) {
                        String a = arrContent[i];
                        String b = arrSubContent[i];

                        EngVietDictSearch item = new EngVietDictSearch(a, b);
                        list.add(item);

                    }
                } else {
                    String[] arrayOfString = data.split("\\|");
                    for (int i = 0; i < arrayOfString.length; i++) {
                        data = arrayOfString[i];
                        if (data.contains("- ")) {
                            data = data.replace("- ", "");
                        }
                        if (data.contains("=")) {
                            data = data.replace("=", "• ");
                            data = data.trim();
                            subcontent += "<font color='#de000000'>&nbsp;&nbsp" + data + "</font><br>";

                        }
                        if (!(data.contains("- ") || (data.contains("= ") || data.contains("• ")))) {
                            data = data.substring(0, 1).toUpperCase().trim() + data.substring(1, data.length()).trim();
                            subcontent += "<p><h7> ☞ <font color=\"#000000\" >  &nbsp;" + data + "</h7></p>";
                        }

                    }
                    list.add(new EngVietDictSearch("", subcontent));
                }

            } else {
                if (data.contains(";")) {
                    String[] arrayOfString = data.trim().split(";");
                    for (int i = 0; i < arrayOfString.length; i++) {
                        //subcontent = arrayOfString[i];
                        String st = arrayOfString[i].trim();
                        if (st.contains("- ")) {
                            st = st.replace("- ", "");
                        }
                        subcontent += "<font color='#de000000'>• &nbsp;&nbsp " + st + "</font><br>";
                    }
                    list.add(new EngVietDictSearch("", subcontent));
                } else if (data.contains("-")) {
                    String[] arrayOfString = data.trim().split("-");
                    for (int i = 0; i < arrayOfString.length; i++) {
                        //subcontent = arrayOfString[i];
                        if (arrayOfString[i].length() > 0) {
                            subcontent += "<p><h7> ☞ <font color=\"#000000\" >  &nbsp;" + arrayOfString[i] + "</h7></p><br>";
                        }
                    }
                    list.add(new EngVietDictSearch("", str));
                }
            }
        }


        return list;
    }

    public static String getPhonicFromMeans(String mean) {
        if (TextUtils.isEmpty(mean)) {
            return Constant.EMPTY_STRING;
        }
        String[] arrayOfString = null;
        //lấy ký tự ở giữa /.../
        if (mean.contains(Constant.SLASH) &&
                (mean.indexOf(Constant.SLASH) != mean.lastIndexOf(Constant.SLASH))) {
            arrayOfString = mean.split(Constant.SLASH);
        }
        //mặc định là empty
        return arrayOfString == null || arrayOfString[1] == null ?
                Constant.EMPTY_STRING : Constant.SLASH + arrayOfString[1] + Constant.SLASH;
    }

    public static String getSimpleMeans(String word, String mean) {
        if (TextUtils.isEmpty(mean)) {
            return Constant.EMPTY_STRING;
        }
        String[] arrayOfString2 = null;
        String tempMean = mean;
        //nếu trong từ khóa có ký tự -, sẽ substring từ ký tự cuối cùng
        if(!TextUtils.isEmpty(word) && word.contains(Constant.MINUS)){
            int lastIndex = word.lastIndexOf(Constant.MINUS);
            tempMean = mean.substring(lastIndex + 2);
        }
        if (tempMean.contains(Constant.MINUS)) {
            //lấy giá trị từ dấu -
            String arrayOfString = tempMean.substring(
                    tempMean.indexOf(Constant.MINUS));
            if (TextUtils.isEmpty(arrayOfString)) {
                return Constant.EMPTY_STRING;
            }
            //nếu có ký tự * sẽ lấy đến *
            if (tempMean.contains(Constant.STAR)) {
                arrayOfString2 = arrayOfString.split(Constant.STAR);
            } else
            //nếu có ký tự = sẽ lấy đến =
            if (tempMean.contains(Constant.EQUAL)) {
                arrayOfString2 = arrayOfString.split(Constant.EQUAL);
            }
            //mặc định lấy toàn bộ
            else {
                arrayOfString2 = new String[]{arrayOfString};
            }
        }
        return arrayOfString2 == null || arrayOfString2[0] == null ?
                Constant.EMPTY_STRING : arrayOfString2[0];
    }
}
