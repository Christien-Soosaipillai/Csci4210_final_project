import java.io.*;
import java.util.Scanner;


public class parser{


    //File Names
    //gdp-per-capita-in-international-and-market-dollars-clean.csv
    //life-expectancy-clean.csv
    //total-healthcare-expenditure-as-share-of-national-gdp-by-country-clean.csv
    //WHOSIS_000004-clean.csv

    public static void main(String[] args)throws IOException {

        //Read file
        String filename = "/home/christien/Documents/School/Parse/src/data/Original-Data/cleandata.csv";

        //Write file

        File file = new File("src/data/Clean/cleandata-clean.csv");



        //country list
        String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos Islands", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands", "Virgin Islands", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};


        try{


            //Create a scanner
            Scanner scanner = new Scanner(new File(filename));
            scanner.useDelimiter("\n");

            //Create a filewriter
            FileWriter f1Writer = new FileWriter(file);

            //Delimiter
            String comma = ",";
            String newLine = "\n";
            String File_Header =  "Entity,Year,Life expectancy";

            //gdp us
            double GDP_US_MIN = 195.7;
            double GDP_US_MAX = 196.0;

            //gdp international
            double GDP_Inter_MIN = 923.7;
            double GDP_Inter_MAX = 924.7;

            //life expectancy
            double life_Exp_Min = 58.8;
            double life_Exp_Max = 59.8;

            //health care percent
            double health_min = 6.9;
            double health_max = 6.9;


            String[] values = null;
            int count = 0;


            f1Writer.append(File_Header + newLine);

            while(scanner.hasNext()){

                    String data = scanner.next();
                    data = data.replace("\"", "");
                    values = data.split(",");
                    System.out.println(data);

                    for(int i =0; i < countries.length;i++) {

                        if(values[0].trim().contains(countries[i])) {

                            if (values[2].trim().contains("2003")||values[2].trim().contains("2004")||values[2].trim().contains("2005")||
                                    values[2].trim().contains("2006")||values[2].trim().contains("2007")||values[2].trim().contains("2008")||
                                    values[2].trim().contains("2009")||values[2].trim().contains("2010")||values[2].trim().contains("2011")||
                                    values[2].trim().contains("2012")||values[2].trim().contains("2013")) {

                                if(values[0] != null){
                                    f1Writer.append(values[0]);
                                    f1Writer.append(comma);
                                    f1Writer.flush();
                                }

//                                if(values[1] != null) {
//                                    f1Writer.append(values[1]);
//                                    f1Writer.append(comma);
//                                }
                                if(values[2] != null) {
                                    f1Writer.append(values[2]);
                                    f1Writer.append(comma);

                                }

                                if(values.length > 3 && values[3] != null){
                                    f1Writer.append(values[3]);
                                    f1Writer.append(comma);

                                    if(Double.parseDouble(values[3]) > GDP_US_MAX ){
                                        GDP_US_MAX = Double.parseDouble(values[3]);

                                    }
                                    if(Double.parseDouble(values[3]) < GDP_US_MIN){
                                        GDP_US_MIN = Double.parseDouble(values[3]);

                                    }

                                }

                                if(values.length > 4 && values[4] != null){
                                    f1Writer.append(values[4]);
                                    f1Writer.append(comma);

                                    if(Double.parseDouble(values[4]) > GDP_Inter_MAX ){
                                        GDP_Inter_MAX = Double.parseDouble(values[4]);

                                    }
                                    if(Double.parseDouble(values[4]) < GDP_Inter_MIN){
                                        GDP_Inter_MIN = Double.parseDouble(values[4]);

                                    }

                                }

                                if(values.length > 5 && values[5] != null){
                                    f1Writer.append(values[5]);
                                    f1Writer.append(newLine);

                                    if(Double.parseDouble(values[5]) > life_Exp_Max){
                                        life_Exp_Max = Double.parseDouble(values[5]);

                                    }
                                    if(Double.parseDouble(values[5]) < life_Exp_Min){
                                        life_Exp_Min = Double.parseDouble(values[5]);

                                    }
                                }

                                if(values.length > 6 && values[6] != null){
                                    f1Writer.append(values[6]);
                                    f1Writer.append(newLine);

                                    if(Double.parseDouble(values[6]) > health_max){
                                        health_max = Double.parseDouble(values[6]);

                                    }
                                    if(Double.parseDouble(values[6]) < health_min){
                                        health_min = Double.parseDouble(values[6]);

                                    }
                                }

                                f1Writer.append(newLine);
                                System.out.println(values[0] + ":" + values[2] + ":" + values[3] );
                                count++;

                            }
                        }

                    }

                //System.out.println(data);


            }
            System.out.println(count+" countries were found for that year");
            System.out.println("GDP_US_MIN=" + GDP_US_MIN );
            System.out.println("GDP_US_MAX=" + GDP_US_MAX );
            System.out.println("GDP_INTER_MIN=" + GDP_Inter_MIN );
            System.out.println("GDP_INTER_MIN=" + GDP_Inter_MAX );
            System.out.println("life min=" + life_Exp_Min );
            System.out.println("life max=" + life_Exp_Max );
            System.out.println("health care min=" + health_min);
            System.out.println("health care max=" + health_max);

            f1Writer.close();
            scanner.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

}