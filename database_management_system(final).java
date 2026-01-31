import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class database_management_system_final {

    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    Scanner input = new Scanner(System.in);
    String currentDirectory = System.getProperty("user.dir");

    while (true) {

    System.out.print(">>");

    String string = scanner.nextLine().trim();
 
    String[] parts = string.split(" ");

    if(parts[0].equalsIgnoreCase("create")){
        _create(parts, input); 
    }

    else if(parts[0].equalsIgnoreCase("drop")){

        drop(parts,input,currentDirectory);

    }
    else if(parts[0].equalsIgnoreCase("show")){

        show(currentDirectory,parts);
    }

    else if(parts[0].equalsIgnoreCase("insert")){

        insert(parts);
    }
    else if (parts[0].equalsIgnoreCase("update")) {
        Update(string);}

    else if (parts[0].equalsIgnoreCase("delete")) {
        Delete(string);}

    else if(parts[0].equalsIgnoreCase("select")){

        select(parts,string);
    }

    else if(string.equals("exit")){
        System.out.println("Exiting the database management system");
        break;
    }

    else if (string.equals("help")) {
        help1();
    }

    else{
        System.out.println("Please enter a valid command");
        System.out.println("Enter help to get correct syntax.");
    }
}

scanner.close();

    }

    public static void _create(String[] parts,Scanner sc){

        if(parts.length != 4){
            System.out.println("Invalid syntax.");
            System.out.println("Correct syntax for create is:");
            System.out.println("CREATE TABLE table_name (column1, column2, column3,…)");
            return;
        }

        else if (!parts[1].equalsIgnoreCase("table")) {
            System.out.println("Invalid syntax.");
            System.out.println("Correct syntax for create is:");
            System.out.println("CREATE TABLE table_name (column1, column2, column3,…)");
            return;
        }

        //getting tableName

        String tableName = parts[2];

        //validating table name according to java identifiers

        while (!tableName.matches("[a-zA-Z_$][a-zA-Z\\d_$]*")){

            System.out.print("Enter valid table name: ");

            tableName = sc.next();

        }

        //getting seperate column names

        String columnNames = parts[3];

        columnNames = columnNames.replace("(", "");

        columnNames = columnNames.replace(")", "");

        String array[] = columnNames.split(",");

        //validating column name

        while (!valid_columns(array, sc)) {
            System.out.print("Enter valid column names (comma-separated): ");
            columnNames = sc.next();
            array = columnNames.split(",");
        }

  
        //creating file using tableName
        boolean file_already_exists = false;
        try {

            File myObj = new File(tableName+".txt");

            if (myObj.createNewFile()) {
            System.out.println("Table " +tableName + " is created");
            
            } else {
            System.out.println("Table already exists.");
            file_already_exists = true;
            }

        } 
        
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        //writing the column names into the file, taake afr file pehlay se bani hoyi ho to new column name se update hojaye
            try {

                FileWriter myWriter = new FileWriter(tableName+".txt");
        
                for (String s: array){
        
                          myWriter.write(s.trim() + "\t");
        
                }
    
                myWriter.close();
        
                if (file_already_exists) {

                    System.out.println("File overwritten with new column names");
                    
                } else {
                    System.out.println("Successfully wrote to the file.");
                }
            
            }
                catch (IOException e) { 
                System.out.println("An error occurred.");
                e.printStackTrace();
        
                  } 
        }

    public static void drop(String[] parts, Scanner input,String currentDir){

        if(parts.length != 3){
            System.out.println("Invalid syntax.");
            System.out.println("Correct syntax for drop is:");
            System.out.println("DROP TABLE table_name");
            return;
        }

        else if (!parts[1].equalsIgnoreCase("table")) {
            System.out.println("Invalid syntax.");
            System.out.println("Correct syntax for drop is:");
            System.out.println("DROP TABLE table_name");
            return;
        }


        String tableName = parts[2];

        System.out.print("Are you sure you want to delete this table? (Y/N): ");

        String confirm = input.next().toLowerCase();

        boolean deleted = false;

        File directory = new File(currentDir);

        if (confirm.equals("y") ) {

        File[] files = directory.listFiles();

        for (File f : files){

        if (f.getName().startsWith(tableName +".txt")){

        deleted = true;

        f.delete();

    System.out.println("Table " + tableName + " is deleted" ); 
} }

    if (deleted == false) {

        System.out.println("Table does not exist");
    }
} }



public static void show(String curerentDir,String[] parts){

    if(parts.length != 2){
        System.out.println("Invalid syntax.");
        System.out.println("Correct syntax for show is:");
        System.out.println("SHOW ALL");
        return;
    }

    else if (!parts[1].equalsIgnoreCase("all")) {
        System.out.println("Invalid syntax.");
        System.out.println("Correct syntax for show is:");
        System.out.println("SHOW ALL");
        return;
    }

    
    // Using File class create an object for specific directory
    File directory = new File(curerentDir);
    
    // Using listFiles method we get all the files of a directory 
    // return type of listFiles is array
    File[] files = directory.listFiles();
    
    // Print name of the all files present in that path
    if (files != null) {

      for (File file : files) {
        //to print only the .txt files
        if (file.getName().endsWith(".txt")) {
            System.out.println(file.getName().replace(".txt", ""));  
        }
      }
    } 
}

    public static void insert(String[] parts){

        if(parts.length != 5){
            System.out.println("Invalid syntax.");
            System.out.println("Correct syntax for insert is:");
            System.out.println("INSERT INTO table_name VALUES (value1, value2, value3, ...)");
            return;
        }
    
        else if (!parts[1].equalsIgnoreCase("into") | !parts[3].equalsIgnoreCase("values") ) {
            System.out.println("Invalid syntax.");
            System.out.println("Correct syntax for insert is:");
            System.out.println("INSERT INTO table_name VALUES (value1, value2, value3, ...)");
            return;
        }

        String tableName = parts[2]; 

                //gettting values with commas:

                String values = parts[4];

                //removing brackets:

                values = values.replace("(", "");

                values = values.replace(")", "");

                //spliting at comma

                String array2[] = values.split(",");;

                try {
                    File tableFile = new File(tableName + ".txt");
            
                    // Check if the file exists
                    if (!tableFile.exists()) {
                        System.out.println("Table file does not exist.");
                        return;
                    }
    
            FileWriter myWriter = new FileWriter(tableFile, true);
            
            myWriter.append("\n");

            for (String s: array2){

                myWriter.append(s.trim() +"\t");

            }
                System.out.println("Successfully inserted values to table");
                myWriter.close();
                    
                } catch (FileNotFoundException e) {
                    System.out.println("Table was not found");
                     
                }
                catch(IOException e){
                    //todo
                }
    }


    public static void select(String[] parts,String string) {

        if(!validate_select_input(parts)){
            System.out.println("Invalid syntax.");
            System.out.println("Correct syntax options are:");
            System.out.println("1. SELECT FROM TABLE table_name");
            System.out.println("2. SELECT FROM TABLE table_name HAVING column = value");
            System.out.println("3. SELECT FROM TABLE table_name HAVING column = value SORT BY column");
            System.out.println("4. SELECT FROM TABLE table_name SORT BY column");
            return;
        }
  
        //select function

        File file = new File(parts[3]+".txt");

        if (!file.exists()) {
            System.out.println("Table file does not exist.");
            return;
        }

        String s = get_columns_rows(file); //to find rows and columns so we can make a 2D array

        String[] column_rows =  s.split("-");

        int columns = Integer.valueOf(column_rows[0]);

        int rows = Integer.valueOf(column_rows[1]);

        String[][] file_array = file_to_array(file,columns,rows);

        //having column = value

        if (string.contains("HAVING") && !string.contains("SORT BY") ){

        String having_column = parts[5];

        String having_column_value = parts[7].replaceAll("\"", "");

        String occurRows = get_occuring_rows(file_array,rows,columns,having_column,having_column_value);

            if (occurRows.equals("column doesn't exist")) {
                System.out.println("The column that we need to find value of doesn't exist");
                return;
            }
            else if (occurRows.equals("no values of column found")) {
                System.out.println("No values of given column were found");
                return;
                
            }

        String[][] having_array = having(file_array,having_column,having_column_value,columns,occurRows);

        print_array_with_header(having_array);

        }

        //having both column = value and sorting

        else if (string.contains("HAVING") && string.contains("SORT BY")){
        String having_column = parts[5];
        String having_column_value = parts[7].replaceAll("\"", "");
        String occurRows = get_occuring_rows(file_array,rows,columns,having_column,having_column_value);

        if (occurRows.equals("column doesn't exist")) {
            System.out.println("The column that we need to find value of doesn't exist");
            return;
        }
        else if (occurRows.equals("no values of column found")) {
            System.out.println("No values of given column were found");
            return;
            
        }

        String[][] unsorted_2d_array = having(file_array,having_column,having_column_value,columns,occurRows);
        String sort_column = parts[10];
        int size_of_1d_arr = occurRows.length();

        print_sorted_array(unsorted_2d_array,sort_column,size_of_1d_arr);

        }

        //only sorting

        else if (!string.contains("HAVING") && string.contains("SORT BY")){
            String sort_column = parts[6];
            int size_of_1d_arr = file_array.length - 1;
            print_sorted_array(file_array, sort_column, size_of_1d_arr);
        }

        //neither having column = value nor sorting

        else if (!string.contains("HAVING") && !string.contains("SORT BY")){

            print_array_with_header(file_array);
        }
    }

//sorting and then printing the unsorted 2D array
public static void print_sorted_array(String[][] unsorted_2d_Array,String sort_column,int size_of_1d_arr){

           //enter elements of givenColumn into 1D array
           String givenColumn = sort_column;
        
           //find index of given column
           int indexOfGivenColumn = -1;
   
           for (int i = 0; i < unsorted_2d_Array[0].length; i++) {
               if(unsorted_2d_Array[0][i].equalsIgnoreCase(givenColumn)){
                   indexOfGivenColumn = i;
                   break;
               } 
           }

           if (indexOfGivenColumn == -1) {
            System.out.println("The sort by column doesn't exist");
            return;
           }
           //create 1D array
            String[] unsorted_1d_arr = new String[size_of_1d_arr];
           //get values from that column of the created array and store them in a 1D array
           int index_of_value_array = 0;
           for (int i = 1; i < unsorted_2d_Array.length; i++) {
   
               unsorted_1d_arr[index_of_value_array] = unsorted_2d_Array[i][indexOfGivenColumn];
               index_of_value_array++;
           }
    //sorting the unsorted 1d array
    String[] sorted_1d_arr = sort_1d_array(unsorted_1d_arr);
    //printing the array with sorted values
    print_header(unsorted_2d_Array);
    System.out.println();

    //reading elements from the sorted 1D array,then finding them in the 2D array of occuringrows,when found print that row from the array
    String printed_rows = "";
    for (String element : sorted_1d_arr) {
        for (int i = 1; i < unsorted_2d_Array.length; i++) {
            for (int j = 0; j < unsorted_2d_Array[0].length; j++) {
                if(unsorted_2d_Array[i][j].equalsIgnoreCase(element)){
                    if (!printed_rows.contains(String.valueOf(i))){
                        for (int k = 0; k < unsorted_2d_Array[0].length; k++) {

                            System.out.printf("%-30s",unsorted_2d_Array[i][k]);                   
                        }
                        System.out.println();
                    }
                    printed_rows = printed_rows + String.valueOf(i);
                }
            }
        }
    }
}

//returning a unsorted 2D array having column = value
public static String[][] having(String[][]main_array,String having_column,String having_column_value,int columns,String occuringRows){

    int rows =  occuringRows.length() +1;
    String[][] array = new String[rows][columns];

    for (int i = 0; i < columns; i++) {
        array[0][i] = main_array[0][i];
    }
    int index = 0;
    for (int i = 1; i <= occuringRows.length(); i++) {
        int occur = Character.getNumericValue(occuringRows.charAt(index));
        index++;
        for (int j = 0; j < columns; j++) {
            array[i][j] = main_array[occur][j]; 
        }
    } 
    return array;
}
//returning the rows where the desired column value occurs
public static String get_occuring_rows(String[][] array,int rows,int columns,String col_name,String col_value){
        int occurColumn = -1;
        for (int m = 0; m < columns; m++){
            if (array[0][m].equalsIgnoreCase(col_name)){
                occurColumn = m;
                break;
            }
        }
        if (occurColumn == -1) {
            return "column doesn't exist";
        }
        String occuringRows = "";
        for (int i = 0; i < rows; i++) {
                if (array[i][occurColumn].equalsIgnoreCase("\""+col_value+"\"")){
                    occuringRows = occuringRows + String.valueOf(i); 
                }
        }
        if (occuringRows == "") {
            return "no values of column found";
        }
        return occuringRows;
}

//copies elements of a TSV to a 2D array
public static String[][] file_to_array(File file,int columns,int rows){
    String[][] twoD_ARR = new String[rows][columns];
    try {
        Scanner input = new Scanner(file);
                int l = 0;
                while (input.hasNextLine()){    
                String s = input.nextLine();
                String[] cols = s.split("\t");
                for(int k = 0; k < columns;k++){
                    twoD_ARR[l][k] = cols[k];
                }
                l++;  
            }
        input.close();
    } 
    catch (FileNotFoundException e) {
        System.out.println("File Not Found");
    }
    return twoD_ARR;
}

//returns columns and rows as string in this format: "columns-rows"
public static String get_columns_rows(File file) {
    String columns_rows ="";
    try {
        Scanner input = new Scanner(file);
        //to find columns: read first line, split at tab, find length of that array
        if (input.hasNextLine()){
            String first_line = input.nextLine();
            String[] array = first_line.split("\t"); 
            int count = array.length;
            columns_rows = columns_rows + String.valueOf(count)  +  "-";
        }
        //finding rows:reading rest of the lines,and then adding 1 to count to include 1st line.
        int count1 = 0;
        while (input.hasNextLine()) {
            input.nextLine();
            count1++; 
        }
        columns_rows = columns_rows + String.valueOf(count1 + 1);
        input.close();
    } 
    catch (FileNotFoundException e) {
        System.out.println("File not Found");
    }
    return columns_rows; }


public static void print_header(String[][] array){

    System.out.println();
    //printing header
    for (int k = 0; k < array[0].length; k++){
        System.out.printf("%-30s",array[0][k]);
        } 
        System.out.println();
    //print bars below header
    for (int k = 0; k < array[0].length; k++){
            System.out.printf("%-30s","------");   
    }
        System.out.println();
}

public static String[] sort_1d_array(String[] array){

    for (int i = 0; i < array.length - 1; i++) {
        int num = array[i].compareTo(array[i+1]);
        if (num > 0){
            String temp = array[i];
            array[i] = array[i+1];
            array[i+1] = temp;
        }   
    }
    for (int i = 0; i < array.length - 1; i++) {
        int num = array[i].compareTo(array[i+1]);
        if (num > 0){
            String temp = array[i];
            array[i] = array[i+1];
            array[i+1] = temp;
        } 
    }
    return array;
}

public static void print_array_with_header(String[][] array){
    print_header(array);
    for (int i = 1; i < array.length; i++) {
        for (int j = 0; j < array[0].length; j++) {
            System.out.printf("%-30s",array[i][j]);
        }
        System.out.println();  
    }
    System.out.println();
}

public static boolean validate_select_input(String[] parts) {
    
    // Case 1: "SELECT FROM TABLE table_name"
    if (parts.length == 4 
            && parts[0].equalsIgnoreCase("SELECT") 
            && parts[1].equalsIgnoreCase("FROM") 
            && parts[2].equalsIgnoreCase("TABLE")) {
        return true;
    }

    // Case 2: "SELECT FROM TABLE table_name HAVING column = value"
    if (parts.length == 8
            && parts[0].equalsIgnoreCase("SELECT") 
            && parts[1].equalsIgnoreCase("FROM") 
            && parts[2].equalsIgnoreCase("TABLE") 
            && parts[4].equalsIgnoreCase("HAVING") 
            && parts[6].equals("=")) {

        return true;
    }

    // Case 3: "SELECT FROM TABLE table_name HAVING column = value SORT BY column"
    if (parts.length == 11
            && parts[0].equalsIgnoreCase("SELECT") 
            && parts[1].equalsIgnoreCase("FROM") 
            && parts[2].equalsIgnoreCase("TABLE") 
            && parts[4].equalsIgnoreCase("HAVING") 
            && parts[6].equals("=") 
            && parts[8].equalsIgnoreCase("SORT") 
            && parts[9].equalsIgnoreCase("BY")) {

        return true;
    }

    // Case 4: "SELECT FROM TABLE table_name SORT BY column"
    if (parts.length == 7 
            && parts[0].equalsIgnoreCase("SELECT") 
            && parts[1].equalsIgnoreCase("FROM") 
            && parts[2].equalsIgnoreCase("TABLE") 
            && parts[4].equalsIgnoreCase("SORT") 
            && parts[5].equalsIgnoreCase("BY")) {

        return true;
    }
        return false;
   }

public static boolean valid_columns(String[] array, Scanner sc) {
    for (String column : array) {
        if (!column.trim().matches("[a-zA-Z_$][a-zA-Z\\d_$]*")) {
            System.out.println("Invalid column name: " + column.trim());
            return false;
        }
    }
    return true;
}


public static void Update(String command) {
    // input

    String[] syntaxParts = command(command);
    if (syntaxParts == null) {
        return; // for invalid syntax
    }

    String fileName = syntaxParts[0];
    String setColumn = syntaxParts[1];
    String newValue = syntaxParts[2];
    String whereColumn = syntaxParts[3];
    String targetValue = syntaxParts[4].toLowerCase();

    String headerLine = "";
    int setColumnIndex = -1;
    int whereColumnIndex = -1;
    int updateCount = 0;  // Counter
    int rowCount = 0;  // Counter

    try {
        BufferedReader reader = new BufferedReader(new FileReader(fileName + ".txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("tempFile.txt"));
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            if (rowCount == 0) {
                // Read the header line to determine column indexes
                headerLine = currentLine;
                String[] headers = headerLine.split("\t");
                for (int i = 0; i < headers.length; i++) {
                    if (headers[i].equalsIgnoreCase(setColumn)) {
                        setColumnIndex = i;
                    }
                    if (headers[i].equalsIgnoreCase(whereColumn)) {
                        whereColumnIndex = i;
                    }
                }

                if (setColumnIndex == -1 || whereColumnIndex == -1) {
                    System.out.println("Column not found in the file.");
                    reader.close();
                    writer.close();
                    return;
                }

                writer.write(headerLine); // load header line to the temp file
                writer.newLine();
                rowCount++;
                continue;
            }

            String[] fields = currentLine.split("\t");
            if (fields[whereColumnIndex].equalsIgnoreCase("\""+targetValue+"\"")){
                // Update the specified column
                fields[setColumnIndex] = "\""+newValue+"\"";
                updateCount++;
                System.out.println(updateCount);
            }

            // load the updated row
            String updatedRow = String.join("\t", fields);
            writer.write(updatedRow);
            writer.newLine();
        }
        reader.close();
        writer.close();

        // del the original file and rename the temp file
        File originalFile = new File(fileName + ".txt");
        if (originalFile.exists()) {
            originalFile.delete();
        }

        File temporaryFile = new File("tempFile.txt");
        if (temporaryFile.exists()) {
            temporaryFile.renameTo(originalFile);
        }

        // updates row no
        System.out.println(updateCount + " rows updated successfully in " + fileName);

        // agr row update na ho to
        if (updateCount == 0) {
            System.out.println("No matching rows found for the specified condition.");
        }

    } catch (IOException ex) {
        System.err.println("Error processing the file: " + ex.getMessage());
    }
}

public static String[] command(String command) {
    command = command.trim().replaceAll("\\s+", " "); // space trim end or strt se
    if (!command.matches("(?i)^UPDATE\\s+\\w+\\s+SET\\s+\\w+\\s*=\\s*\"[^\"]+\"\\s+WHERE\\s+\\w+\\s*=\\s*\"[^\"]+\"$")) {
        System.out.println("Invalid syntax. Correct syntax: UPDATE table_name SET column = value WHERE column = value");
        return null;
    }

    // extract using regex
    String tableName = command.split("\\s+")[1];
    String setColumn = command.split("SET")[1].split("=")[0].trim();
    String newValue = command.split("SET")[1].split("=")[1].split("WHERE")[0].trim().replaceAll("\"", "");
    String whereColumn = command.split("WHERE")[1].split("=")[0].trim();
    String targetValue = command.split("WHERE")[1].split("=")[1].trim().replaceAll("\"", "");

    return new String[]{tableName, setColumn, newValue, whereColumn, targetValue};
}

public static void Delete(String command) {  
    // input

    String[] syntaxParts = parseDeleteCommand(command); 
    if (syntaxParts == null) {
        return; // for invalid syntax
    }

    String fileName = syntaxParts[0];
    String whereColumn = syntaxParts[1];
    String targetValue = syntaxParts[2].toLowerCase();

    String headerLine = "";
    int whereColumnIndex = -1;
    int deleteCount = 0;  // Counter
    int rowCount = 0;  // Counter

    try {
        BufferedReader reader = new BufferedReader(new FileReader(fileName + ".txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("tempFile.txt"));
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            if (rowCount == 0) {
                // Read the header line to determine column indexes
                headerLine = currentLine;
                String[] headers = headerLine.split("\t");
                for (int i = 0; i < headers.length; i++) {
                    if (headers[i].equalsIgnoreCase(whereColumn)) {
                        whereColumnIndex = i;
                    }
                }

                if (whereColumnIndex == -1) {
                    System.out.println("Column not found in the file.");
                    reader.close();
                    writer.close();
                    return;
                }

                writer.write(headerLine); // load header line to the temp file
                writer.newLine();
                rowCount++;
                continue;
            }

            String[] fields = currentLine.split("\t");
            if (!fields[whereColumnIndex].equalsIgnoreCase("\"" + targetValue + "\"")) {
                // Copy row to the temp file if it doesn't match the condition
                String updatedRow = String.join("\t", fields);
                writer.write(updatedRow);
                writer.newLine();
            } else {
                deleteCount++; // Increment delete count for matching rows
            }
        }
        reader.close();
        writer.close();

        // del the original file and rename the temp file
        File originalFile = new File(fileName + ".txt");
        if (originalFile.exists()) {
            originalFile.delete();
        }

        File temporaryFile = new File("tempFile.txt");
        if (temporaryFile.exists()) {
            temporaryFile.renameTo(originalFile);
            System.out.println("tempfilecreated");
        }

        // Confirmation message
        System.out.println(deleteCount + " rows deleted successfully in " + fileName);

        // If no rows were deleted
        if (deleteCount == 0) {
            System.out.println("No matching rows found for the specified condition.");
        }

    } catch (IOException ex) {
        System.err.println("Error processing the file: " + ex.getMessage());
    }
}

public static String[] parseDeleteCommand(String command) {  
    command = command.trim().replaceAll("\\s+", " "); // space trim end or start
    if (!command.matches("(?i)^DELETE\\s+FROM\\s+\\w+\\s+WHERE\\s+\\w+\\s*=\\s*\"[^\"]+\"$")) {
        System.out.println("Invalid syntax. Correct syntax: DELETE FROM table_name WHERE column = value");
        return null;
    }

    // extract using regex
    String tableName = command.split("\\s+")[2];
    String whereColumn = command.split("WHERE")[1].split("=")[0].trim();
    String targetValue = command.split("WHERE")[1].split("=")[1].trim().replaceAll("\"", "");

    return new String[]{tableName, whereColumn, targetValue};
}


    
public static void help1() {
    System.out.println("      Help      ");
    System.out.println("CREATE TABLE table_name (column1, column2, column3,…)");
    System.out.println("DROP TABLE table_name");
    System.out.println("SHOW ALL");
    System.out.println("INSERT INTO table_name VALUES (value1, value2, value3, ...)");
    System.out.println("SELECT FROM TABLE table_name HAVING column = value SORT BY column");
    System.out.println("UPDATE table_name SET column = value WHERE column = value");
    System.out.println("DELETE FROM table_name WHERE column = value");
}
}