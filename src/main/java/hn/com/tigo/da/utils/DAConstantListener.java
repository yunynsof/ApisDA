package hn.com.tigo.da.utils;

/**
 * Class InvoiceConstantListener.
 * 
 * @author Kerim Fortin
 * @since 16/09/2020
 */
public final class DAConstantListener {	
	
    /** Attribute that determine the Constant CODE_ERROR. */
    public static final String CODE_ERROR = "-1";

    /** Attribute that determine the Constant BANK_FILE_NOT_FOUND. */
    public static final String BANK_FILE_NOT_FOUND = "Bank File Not Found";
            
    /** Attribute that determine the Constant BANK_INCLU_EXCLU_NOT_FOUND. */
    public static final String BANK_INCLU_EXCLU_NOT_FOUND = "Bank Inclu And Exclu Not Found";
    
    public static final String BANK_EXCLU_AUTOPAY_NOT_FOUND = "Bank Exclu AutoPay Not Found";
    
    /** Attribute that determine the Constant BANK_DELETE_ACCOUNT_NO_DATA. */
    public static final String BANK_DELETE_ACCOUNT_NO_DATA = "Bank Delete Account not data";
    
    /** Attribute that determine the Constant CODE_SUCCESS. */
    public static final String CODE_SUCCESS = "0";
    
    /** Attribute that determine the Constant CODE_NO_DATA. */
    public static final String CODE_NO_DATA = "100";
    
    /** Attribute that determine the Constant MESSAGE_NO_DATA. */
    public static final String MESSAGE_NO_DATA = "No data found.";
    
    /** Attribute that determine the Constant OPERATION_SUCCESS. */
    public static final String OPERATION_SUCCESS = "Operation Success.";
    
    /** Attribute that determine the Constant MESSAGE_SUCESS. */
    public static final String MESSAGE_SUCESS = "Operation successful.";
        
    /** Attribute that determine the Constant MESSAGE_ERROR_DB. */
    public static final String MESSAGE_ERROR_DB = "Error ocurred in data base";
    
    /** Attribute that determine the Constant MESSAGE_CLOSE_DB. */
    public static final String MESSAGE_CLOSE_DB = "Could not close the database";
    
    /** Attribute that determine the Constant TRANSACTION_COMPLETE. */
    public static final String TRANSACTION_COMPLETE = "Transaction Complete!!!";
    
    /** Attribute that determine the Constant ERROR_DB. */
    public static final String ERROR_DB = "An error has occurred while querying the database.";
    
    /** Attribute that determine the Constant NO_CLOSE_DB. */
    public static final String NO_CLOSE_DB = "Could not close the database";
    
    /** Attribute that determine the Constant NO_DATA_FOR_CYCLE. */
    public static final String NO_DATA_FOR_CYCLE = "No data for cycle. ";
    
    /** Attribute that determine the Constant CODE_SUCCESS. */
    public static final String NOT_FILE = "Es requerido el archivo del banco";
    
    // ---------------------- PARAMS IN APIS ------------------------------------------------------------------------
    /** Attribute that determine the Constant CYCLE. */
    public static final String CYCLE = "cycle";
    
    /** Attribute that determine the Constant UUID. */
    public static final String UUID = "uuid";
    
    public static final String FILE = "file";
    
    /** Attribute that determine the Constant UUID. */
    public static final String ID_BANK_PROCESS = "idBankProcess";
    
    /** Attribute that determine the Constant NUMBER. */
    public static final String NUMBER = "number";
    
    /** Attribute that determine the Constant NUMBER. */
    public static final String DATE_INIT = "dateInit";
    
    /** Attribute that determine the Constant NUMBER. */
    public static final String DATE_END = "dateEnd";
    
    // --------------------------------- SWAGGER --------------------------------------------------------------------
    /** Attribute that determine the Constant ERROR_302_MESSAGE. */
    public static final String ERROR_302_MESSAGE = "Found";
    
    /** Attribute that determine the Constant ERROR_400_MESSAGE. */
    public static final String ERROR_400_MESSAGE = "Bad request";
    
    /** Attribute that determine the Constant ERROR_401_MESSAGE. */
    public static final String ERROR_401_MESSAGE = "Unauthorized";
    
    /** Attribute that determine the Constant ERROR_403_MESSAGE. */
    public static final String ERROR_403_MESSAGE = "Forbidden";
    
    /** Attribute that determine the Constant ERROR_404_MESSAGE. */
    public static final String ERROR_404_MESSAGE = "Not Found";
    
    /** Attribute that determine the Constant ERROR_500_MESSAGE. */
    public static final String ERROR_500_MESSAGE = "Internal server error";
    
    /** Attribute that determine the Constant GET_QUERY_BY_CYCLE_200. */
    public static final String GET_QUERY_BY_CYCLE_200 = "Query by cycle, completed successfully";
    
        /** Attribute that determine the Constant GET_QUERY_BY_CYCLE_200. */
    public static final String GET_QUERY_EXCLU_AUTOPAY_200 = "Query exclu autopay, completed successfully";
    
    /** Attribute that determine the Constant GET_QUERY_BY_CYCLE_200. */
    public static final String SET_NOTIFY_ADDRESS_200 = "updated notify address, completed successfully";
    
    /** Attribute that determine the Constant GET_QUERY_BY_CYCLE_200. */
    public static final String GET_QUERY_BY_CYCLE_VALUE = "Query by cycle, completed successfully";
    
    public static final String GET_INVOICE_PDF_UUID_VALUE = "";
    public static final String SET_NOTIFY_ADDRESS = "Used for update notify address emails";
    
    public static final String NOTIFY_DA_1 = "Inicio del procesamiento del archivo de salida";
    
    public static final String MESSAGE_PROCESS_CYCLE = "Existe un ciclo vigente pendiente, por favor culmine ciclo anterior para crear uno nuevo ";
}
