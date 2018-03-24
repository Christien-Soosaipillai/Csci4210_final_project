import java.io.*;
import java.util.Scanner;


public class parser{





    public static void main(String[] args)throws IOException {

        //Read file
        String filename = "data/the-share-of-people-living-in-extreme-poverty-vs-gdp-per-capita.csv";

        //Write file
        String newFile = "data/clean/the-share-of-people-living-in-extreme-poverty-vs-gdp-per-capita-clean.csv";
        File file = new File(filename);

        //country list
        String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands", "Virgin Islands", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};


        try{


            //Create a scanner
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\n");

            //Create a filewriter
            FileWriter f1Writer = new FileWriter(newFile);

            //Delimiter
            String comma = ",";
            String newLine = "\n";
            String File_Header =  "Entity, Code, Year, (current US$), (current international $)";

            String[] values = null;
            int count = 0;


            f1Writer.append(File_Header);

            while(scanner.hasNext()){

                    String data = scanner.next();
                    values = data.split(",");

                    for(int i =0; i < countries.length;i++) {

                        if(values[0].trim().contains(countries[i])) {

                            if (values[2].trim().contains("2003")||values[2].trim().contains("2004")||values[2].trim().contains("2005")||
                                    values[2].trim().contains("2006")||values[2].trim().contains("2007")||values[2].trim().contains("2008")||
                                    values[2].trim().contains("2009")||values[2].trim().contains("2010")||values[2].trim().contains("2011")||
                                    values[2].trim().contains("2012")||values[2].trim().contains("2013")) {

                                if(values[0] != null){
                                    f1Writer.append(values[0]);
                                    f1Writer.append(comma);
                                }

                                //if(values[1] != null) {
                                    //fWriter.append(values[1]);
                                    //fWriter.append(comma);
                                //}
                                if(values[2] != null) {
                                    f1Writer.append(values[2]);
                                    f1Writer.append(comma);
                                }

                                if(values.length > 3 && values[3] != null){
                                    f1Writer.append(values[3]);
                                    f1Writer.append(comma);
                                }

                                if(values.length > 4 && values[4] != null){
                                    f1Writer.append(values[4]);
                                    f1Writer.append(comma);
                                }

                                if(values.length > 5 && values[5] != null){
                                    f1Writer.append(values[5]);
                                    f1Writer.append(newLine);
                                }
                                System.out.println(values[0] + ":" + values[2] + ":" + values[3] );
                                count++;

                            }
                        }

                    }

                //System.out.println(data);


            }
            System.out.println(count+" countries were found for that year");
            f1Writer.close();
            scanner.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

}