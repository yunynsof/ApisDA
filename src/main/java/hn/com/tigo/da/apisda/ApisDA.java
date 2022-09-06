package hn.com.tigo.da.apisda;

import com.google.gson.Gson;
import com.tigo.enterprise.resources.parameters.simple.v1.schema.ParameterArray;
import com.tigo.enterprise.resources.parameters.simple.v1.schema.ParameterType;
import com.tigo.josm.gateway.services.order.additionalparameterdto.v1.AdditionalParameters;
import com.tigo.josm.gateway.services.order.additionalparameterdto.v1.Parameter;
import com.tigo.josm.gateway.services.order.orderresponse.v1.OrderResponse;
import com.tigo.josm.gateway.services.order.simpleorderrequest.v1.SimpleOrderRequest;

import hn.com.tigo.da.dto.AccountBillDTO;
import hn.com.tigo.da.dto.ApproveCyclesDTO;
import hn.com.tigo.da.dto.ApproverModel;
import hn.com.tigo.da.dto.ApproversBankDTO;
import hn.com.tigo.da.dto.BankProcessDTO;
import hn.com.tigo.da.dto.BankProcessModel;
import hn.com.tigo.da.dto.BankReProcessDTO;
import hn.com.tigo.da.dto.CardInfoDTO;
import hn.com.tigo.da.dto.ConfigBanksDTO;
import hn.com.tigo.da.dto.ConfigCyclesDTO;
import hn.com.tigo.da.dto.ConfigErrorDTO;
import hn.com.tigo.da.dto.ConfigStatusDTO;
import hn.com.tigo.da.dto.ConfigYearsDTO;
import hn.com.tigo.da.dto.DABankProcessDetailPayDTO;
import hn.com.tigo.da.dto.DeleteListModel;
import hn.com.tigo.da.dto.DeleteMpDTO;
import hn.com.tigo.da.dto.DeleteMpModel;
import hn.com.tigo.da.dto.DetailReprocessDTO;
import hn.com.tigo.da.dto.DetailsReprocessDTO;
import hn.com.tigo.da.dto.EmailsApproversDTO;
import hn.com.tigo.da.dto.IncluAndExcluDTO;
import hn.com.tigo.da.dto.OutputfileDTO;
import hn.com.tigo.da.dto.ProcessDetailPayDTO;
import hn.com.tigo.da.dto.ReprocessFileDTO;
import hn.com.tigo.da.dto.StatsCycleDTO;
import hn.com.tigo.da.dto.TwoLastCycleDTO;
import hn.com.tigo.da.manager.DAManager;
import hn.com.tigo.da.manager.InvoiceManager;
import hn.com.tigo.da.responses.AllDetailResponse;
import hn.com.tigo.da.responses.ApplyPayResponse;
import hn.com.tigo.da.responses.ApproveCyclesResponse;
import hn.com.tigo.da.responses.BankProcessResponse;
import hn.com.tigo.da.responses.CardInfoResponse;
import hn.com.tigo.da.responses.ConfigApiResponse;
import hn.com.tigo.da.responses.DetailsReprocessResponse;
import hn.com.tigo.da.responses.FileNameResponse;
import hn.com.tigo.da.responses.GeneralResponse;
import hn.com.tigo.da.responses.GraphicsResponse;
import hn.com.tigo.da.responses.IncluAndExclulResponse;
import hn.com.tigo.da.responses.LogErrorResponse;
import hn.com.tigo.da.responses.NoCardResponse;
import hn.com.tigo.da.responses.OutPutFilesResponse;
import hn.com.tigo.da.responses.ReportMpResponse;
import hn.com.tigo.da.responses.ResponseDeleteAccount;
import hn.com.tigo.da.responses.SelectDeleteMpResponse;
import hn.com.tigo.da.responses.SelectPayResponse;
import hn.com.tigo.da.responses.SyncAccountResponse;
import hn.com.tigo.da.service.EmailServiceProvider;
import hn.com.tigo.da.swagger.model.CardInfoSwaggerModel;
import hn.com.tigo.da.utils.ApiDAUtils;
import hn.com.tigo.da.utils.DAConstantListener;
import hn.com.tigo.da.utils.DASwaggerConstant;
import hn.com.tigo.da.utils.EncryptDecrypt;
import hn.com.tigo.da.utils.ExcelDecode;
import hn.com.tigo.da.utils.NewRelicImpl;
import hn.com.tigo.da.utils.SingletonDALocal;
import hn.com.tigo.da.utils.ValidateCard;
import hn.com.tigo.da.utils.ValidateCardResponse;
import hn.com.tigo.da.utils.ValidateParam;
import hn.com.tigo.josm.adapter.requesttype.v1.TaskRequestType;
import hn.com.tigo.josm.adapter.requesttype.v1.TaskResponseType;
import hn.com.tigo.josm.gateway.services.gateway.ExecuteOrderService;
import hn.com.tigo.josm.gateway.services.gateway.Order;
import hn.com.tigo.josm.persistence.core.ServiceSessionEJB;
import hn.com.tigo.josm.persistence.core.ServiceSessionEJBLocal;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import hn.com.tigo.resources.emailservice.SentDTO;
import hn.com.tigo.resources.emailservice.ToDto;
import hn.com.tigo.tool.annotations.api.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import tigo.com.invoice.utils.Constants;
import tigo.com.invoice.utils.GenericException;
import hn.com.tigo.resources.constants.DAListenerConstants;

/**
 * REST Web Service
 *
 * @author Kerim Fortin
 */
@Path("da")
@Api(value = "ApisDA", produces = MediaType.APPLICATION_JSON, description = "Debito Automatico 2.0 api configuration")
public class ApisDA {

    @Context
    private UriInfo context;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApisDA.class);
    private final ValidateParam validateParam;
    private final ValidateCard validateCard;

    private HashMap<String, String> configParams;

    @EJB
    SingletonDALocal sLDa;

    public ApisDA() {
        validateParam = new ValidateParam();
        validateCard = new ValidateCard();
    }

    @GET
    @Path("/DAGetCardInfoByClientApi/{type}/{param}")
    @Log(value = "Bank", valueMethod = "/DAGetCardInfoByClientApi/{type}/{param}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_CARD_INFO_200, response = CardInfoResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_CARD_INFO_VALUE, response = CardInfoResponse.class)
    public Response dAGetCardInfoByClientApi(@ApiParam(name = "type", example = "acct", required = true, value = "String value") @PathParam("type") String type, @ApiParam(name = "param", example = "4000165999", required = true, value = "String value") @PathParam("param") String param, @QueryParam("getCard") String getCard) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        InvoiceManager manager2 = null;
        List<CardInfoDTO> response = null;
        CardInfoResponse cardInfoResponse = new CardInfoResponse();
        try {
            try {
                this.validateParam.validatedGetCardInfoByClientApi(type, param);
            } catch (GenericException e) {
                cardInfoResponse.setCode(DAConstantListener.CODE_ERROR);
                cardInfoResponse.setDescription(e.getMessage());
                return Response.ok(cardInfoResponse).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getCarInfo(type, param);
            if (response.isEmpty()) {
                cardInfoResponse.setCode(DAConstantListener.CODE_NO_DATA);
                cardInfoResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DAGetCardInfoByClientApi No data found.");
            } else {
                ServiceSessionEJBLocal<InvoiceManager> serviceSession2 = ServiceSessionEJB.getInstance();
                manager2 = (InvoiceManager) serviceSession2.getSessionDataSource(InvoiceManager.class, "Invoice");

                AccountBillDTO dtoAccount = manager2.getEmailsAccountBill(response.get(0).getAcctCode());
                if (dtoAccount != null) {
                	if(dtoAccount.getEmail()!= null) {
                      if (dtoAccount.getEmail().split(";").length > 0) {
                        response.get(0).setEmail(dtoAccount.getEmail().split(";"));
                      }
                   }
                }
                String noCard = EncryptDecrypt.decript(response.get(0).getNoCard());
                if (getCard == null) {
                    response.get(0).setNoCard(String.format("%" + noCard.length() + "s", new Object[]{noCard.substring(noCard.length() - 4)}).replace(' ', '*'));
                } else {
                    if ("1".equalsIgnoreCase(getCard)) {
                        response.get(0).setNoCard(noCard);
                    } else {
                        response.get(0).setNoCard(String.format("%" + noCard.length() + "s", new Object[]{noCard.substring(noCard.length() - 4)}).replace(' ', '*'));
                    }
                }

                cardInfoResponse.setCode(DAConstantListener.CODE_SUCCESS);
                cardInfoResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                cardInfoResponse.setCardInfo(response.get(0));
            }
            return Response.ok(cardInfoResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            cardInfoResponse.setCode(DAConstantListener.CODE_ERROR);
            cardInfoResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(cardInfoResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
            if (manager2 != null) {
                try {
                    manager2.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DASelectCardInfo/{id}")
    @ApiOperation(value = "", hidden = true)
    @Log(value = "Bank", valueMethod = "/DASelectCardInfo/{id}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dASelectCardInfo(@PathParam("id") String id) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        CardInfoDTO response = null;
        NoCardResponse noCardResponse = new NoCardResponse();
        try {
            try {
                this.validateParam.validatedSelectCardInfo(id);
            } catch (GenericException e) {
                noCardResponse.setCode(DAConstantListener.CODE_ERROR);
                noCardResponse.setDescription(e.getMessage());
                return Response.ok(noCardResponse).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getCarInfoById(id);
            if (response == null) {
                noCardResponse.setCode(DAConstantListener.CODE_NO_DATA);
                noCardResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DASelectCardInfo No data found.");
            } else {
                noCardResponse.setCode(DAConstantListener.CODE_SUCCESS);
                noCardResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                noCardResponse.setCardNo(EncryptDecrypt.decript(response.getNoCard()));
            }
            return Response.ok(noCardResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            noCardResponse.setCode(DAConstantListener.CODE_ERROR);
            noCardResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(noCardResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DASelectPayDApi/{type}/{param}")
    @Log(value = "Bank", valueMethod = "/DASelectPayDApi/{type}/{param}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_SELECT_PAY_200, response = SelectPayResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_SELECT_PAY_VALUE, response = SelectPayResponse.class)
    public Response daSelectPayDApi(@ApiParam(name = "type", example = "acct", required = true, value = "String value") @PathParam("type") String type, @ApiParam(name = "param", example = "4000165999", required = true, value = "String value") @PathParam("param") String param) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<ProcessDetailPayDTO> response = null;
        SelectPayResponse selectPayResponse = new SelectPayResponse();
        try {
            try {
                this.validateParam.validatedGetCardInfoByClientApi(type, param);
            } catch (GenericException e) {
                selectPayResponse.setCode(DAConstantListener.CODE_ERROR);
                selectPayResponse.setDescription(e.getMessage());
                return Response.ok(selectPayResponse).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getProcessDetailPay(type, param);
            if (response.isEmpty()) {
                selectPayResponse.setCode(DAConstantListener.CODE_NO_DATA);
                selectPayResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DASelectPayDApi No data found.");
            } else {
                selectPayResponse.setCode(DAConstantListener.CODE_SUCCESS);
                selectPayResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                selectPayResponse.setProcessDetailPay(response);
            }
            return Response.ok(selectPayResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            selectPayResponse.setCode(DAConstantListener.CODE_ERROR);
            selectPayResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(selectPayResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DASelectIncluExcluApi/{type}/{param}")
    @Log(value = "Bank", valueMethod = "/DASelectIncluExcluApi/{type}/{param}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_SELECT_INCLU_AND_EXCLU_200, response = IncluAndExclulResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_SELECT_INCLU_AND_EXCLU_VALUE, response = IncluAndExclulResponse.class)
    public Response daSelectIncluExcluApi(@ApiParam(name = "type", example = "acct", required = true, value = "String value") @PathParam("type") String type, @ApiParam(name = "param", example = "4000165999", required = true, value = "String value") @PathParam("param") String param) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<IncluAndExcluDTO> response = null;
        IncluAndExclulResponse incluAndExclulResponse = new IncluAndExclulResponse();
        try {
            try {
                this.validateParam.validatedGetCardInfoByClientApi(type, param);
            } catch (GenericException e) {
                incluAndExclulResponse.setCode(DAConstantListener.CODE_ERROR);
                incluAndExclulResponse.setDescription(e.getMessage());
                return Response.ok(incluAndExclulResponse).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getIncluAndExclu(type, param);
            if (response.isEmpty()) {
                incluAndExclulResponse.setCode(DAConstantListener.CODE_NO_DATA);
                incluAndExclulResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DASelectIncluExcluApi No data found.");
            } else {
                incluAndExclulResponse.setCode(DAConstantListener.CODE_SUCCESS);
                incluAndExclulResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                incluAndExclulResponse.setIncluAndExclu(response);
            }
            return Response.ok(incluAndExclulResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            incluAndExclulResponse.setCode(DAConstantListener.CODE_ERROR);
            incluAndExclulResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(incluAndExclulResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DAConfigApi")
    @Log(value = "Bank", valueMethod = "/DAConfigApi", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_CONFIG_PARAMS_200, response = ConfigApiResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_CONFIG_PARAMS_VALUE, response = ConfigApiResponse.class)
    public Response daConfigApi(@PathParam("cycle") String type) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<ConfigCyclesDTO> configCyclesDTO = null;
        List<ConfigBanksDTO> configBanksDTO = null;
        List<ConfigYearsDTO> configYearsDTO = null;
        List<ConfigStatusDTO> configStatusDTO = null;
        List<TwoLastCycleDTO> lastCycleBanks = null;
        List<ConfigErrorDTO> configErrorDTO = null;
        ConfigApiResponse configApiResponse = new ConfigApiResponse();
        try {
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            configCyclesDTO = manager.getConfigCycles();
            configBanksDTO = manager.getConfigBanks();
            configYearsDTO = manager.getConfigYears();
            configStatusDTO = manager.getConfigStatus();
            configErrorDTO = manager.getConfigError();
            lastCycleBanks = manager.getLastCycleBanks();
            configApiResponse.setCode(DAConstantListener.CODE_SUCCESS);
            configApiResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
            configApiResponse.setConfigCycles(configCyclesDTO);
            configApiResponse.setConfigBanks(configBanksDTO);
            configApiResponse.setConfigYears(configYearsDTO);
            configApiResponse.setConfigStatus(configStatusDTO);
            configApiResponse.setConfigError(configErrorDTO);
            configApiResponse.setLastCycleBanks(lastCycleBanks);
            return Response.ok(configApiResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            configApiResponse.setCode(DAConstantListener.CODE_ERROR);
            configApiResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(configApiResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DADownloadFilesApi/{id}")
    @Log(value = "Bank", valueMethod = "/DADownloadFilesApi/{id}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_DOWLOAD_OUTPUT_FILES_200, response = OutPutFilesResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_DOWLOAD_OUTPUT_FILES_VALUE, response = OutPutFilesResponse.class)
    public Response daDownloadFilesApi(@ApiParam(name = "id", example = "fsdy44-5345-2356-fdfsHdsd8-32423", required = true, value = "String value") @PathParam("id") String id) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        OutputfileDTO response = null;
        OutPutFilesResponse outPutFilesResponse = new OutPutFilesResponse();
        try {
            try {
                this.validateParam.validatedIdProcessBank(id);
            } catch (GenericException e) {
                outPutFilesResponse.setCode(DAConstantListener.CODE_ERROR);
                outPutFilesResponse.setDescription(e.getMessage());
                return Response.ok(outPutFilesResponse).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getOutputsFile(id);
            if (response != null) {
                outPutFilesResponse.setCode(DAConstantListener.CODE_SUCCESS);
                outPutFilesResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                outPutFilesResponse.setOutPutFiles(response);
            } else {
                outPutFilesResponse.setCode(DAConstantListener.CODE_NO_DATA);
                outPutFilesResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DADownloadFilesApi No data found.");
            }
            return Response.ok(outPutFilesResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            outPutFilesResponse.setCode(DAConstantListener.CODE_ERROR);
            outPutFilesResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(outPutFilesResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DAPreSendApi/{process}/{type}/{param}")
    @Log(value = "Bank", valueMethod = "/DAPreSendApi/{process}/{type}/{param}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.PRE_SEND_PAY_200, response = SelectPayResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.PRE_SEND_PAY_VALUE, response = SelectPayResponse.class)
    public Response DAPreSendApi(@ApiParam(name = "process", example = "fsdy44-5345-2356-fdfsHdsd8-32423", required = true, value = "String value") @PathParam("process") String process, @ApiParam(name = "type", example = "acct", required = true, value = "String value") @PathParam("type") String type, @ApiParam(name = "param", example = "4000165999", required = true, value = "String value") @PathParam("param") String param) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<ProcessDetailPayDTO> response = null;
        SelectPayResponse selectPayResponse = new SelectPayResponse();
        try {
            try {
                this.validateParam.validatedGetDetailProcess(process, type, param);
            } catch (GenericException e) {
                selectPayResponse.setCode(DAConstantListener.CODE_ERROR);
                selectPayResponse.setDescription(e.getMessage());
                return Response.ok(selectPayResponse).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getProcessDetailPayByProcess(type, param, process);
            if (response.isEmpty()) {
                selectPayResponse.setCode(DAConstantListener.CODE_NO_DATA);
                selectPayResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DAPreSendApi No data found.");
            } else {
                selectPayResponse.setCode(DAConstantListener.CODE_SUCCESS);
                selectPayResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                selectPayResponse.setProcessDetailPay(response);
            }
            return Response.ok(selectPayResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            selectPayResponse.setCode(DAConstantListener.CODE_ERROR);
            selectPayResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(selectPayResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DASelectProcessCycleApi")
    @Log(value = "Bank", valueMethod = "/DASelectProcessCycleApi", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_INFORMATION_BEFORE_PROCESSING_200, response = BankProcessResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_INFORMATION_BEFORE_PROCESSING_VALUE, response = BankProcessResponse.class)
    public Response daSelectProcessCycleApi() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<BankProcessDTO> response = null;
        BankProcessResponse bankProcessResponse = new BankProcessResponse();
        try {
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getBankProcessCycles();
            if (response.isEmpty()) {
                bankProcessResponse.setCode(DAConstantListener.CODE_NO_DATA);
                bankProcessResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DASelectProcessCycleApi No data found.");
            } else {
                bankProcessResponse.setCode(DAConstantListener.CODE_SUCCESS);
                bankProcessResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                bankProcessResponse.setBankProcessCycles(response);
            }
            return Response.ok(bankProcessResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            bankProcessResponse.setCode(DAConstantListener.CODE_ERROR);
            bankProcessResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(bankProcessResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DASelectReProcessCycleApi")
    @Log(value = "Bank", valueMethod = "/DASelectReProcessCycleApi", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.REPROCESS_FILE_200, response = BankProcessResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.REPROCESS_FILE_VALUE, response = BankProcessResponse.class)
    public Response daSelectReprocessCycleApi() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<BankProcessDTO> response = null;
        BankProcessResponse bankProcessResponse = new BankProcessResponse();
        try {
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getReprocessBank();
            if (response.isEmpty()) {
                bankProcessResponse.setCode(DAConstantListener.CODE_NO_DATA);
                bankProcessResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DASelectProcessCycleApi No data found.");
            } else {
                bankProcessResponse.setCode(DAConstantListener.CODE_SUCCESS);
                bankProcessResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                bankProcessResponse.setBankProcessCycles(response);
            }
            return Response.ok(bankProcessResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            bankProcessResponse.setCode(DAConstantListener.CODE_ERROR);
            bankProcessResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(bankProcessResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DAGetApproversApi/{cycle}/{type}/{user}")
    @Log(value = Constants.DATASOURCE, valueMethod = "/DAGetApproversApi/{cycle}/{type}/{user}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_APPROVE_REQUEST_200, response = ApproveCyclesResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_APPROVE_REQUEST_VALUE, response = ApproveCyclesResponse.class)
    public Response getSolicitudAprobacionCiclo(@ApiParam(name = "cycle", example = "fsdy44-5345-2356-fdfsHdsd8-32423", required = true, value = "String value") @PathParam("cycle") String cycle, @ApiParam(name = "type", example = "1", required = true, value = "Numeric type") @PathParam("type") String type, @ApiParam(name = "user", example = "Edwin.Zambrano", required = true, value = "String value") @PathParam("user") String user) {
        DAManager manager = null;
        ApproveCyclesDTO dto = null;
        final ApproveCyclesResponse response = new ApproveCyclesResponse();
        try {
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            ApproversBankDTO dtoAprobador = manager.existAprobadorCiclo(user, type);
            if (dtoAprobador != null) {
                response.setPermiso(true);
                int nType = 0;
                if ("1".equalsIgnoreCase(type)) {
                    nType = 20;
                } else if ("2".equalsIgnoreCase(type)) {
                    nType = 21;
                }
                dto = manager.getSolicitudAprobacion(cycle, nType);
                if (dto == null) {
                    response.setCode(DAConstantListener.CODE_NO_DATA);
                    response.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                    NewRelicImpl.addNewRelicErrorMessage("ApisDA/DAGetApproversApi No data found.");
                } else {
                    response.setCode(DAConstantListener.CODE_SUCCESS);
                    response.setDescription(DAConstantListener.MESSAGE_SUCESS);
                    response.setSolicitudAprobacion(dto);
                }
            } else {
                response.setCode(DAConstantListener.CODE_NO_DATA);
                response.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                response.setPermiso(false);
            }

            return Response.ok(response).cacheControl(new CacheControl()).type(MediaType.APPLICATION_JSON).build();
        } catch (PersistenceException e) {
            response.setCode(DAConstantListener.CODE_NO_DATA);
            response.setDescription(e.getMessage());
            NewRelicImpl.addNewRelicError(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).type(MediaType.APPLICATION_JSON).entity(response).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DAGraphicsApi/{id}")
    @Log(value = "Bank", valueMethod = "/DAGraphicsApi/{id}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_GRAPH_200, response = GraphicsResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_GRAPH_VALUE, response = GraphicsResponse.class)
    public Response daGraphicsApi(@ApiParam(name = "id", example = "fsdy44-5345-2356-fdfsHdsd8-32423", required = true, value = "String value") @PathParam("id") String id) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        StatsCycleDTO response = null;
        GraphicsResponse graphicsResponse = new GraphicsResponse();
        try {
            try {
                this.validateParam.validatedIdProcessBank(id);
            } catch (GenericException e) {
                graphicsResponse.setCode(DAConstantListener.CODE_ERROR);
                graphicsResponse.setDescription(e.getMessage());
                return Response.ok(graphicsResponse).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getStatsCycle(id);
            if (response != null) {
                graphicsResponse.setCode(DAConstantListener.CODE_SUCCESS);
                graphicsResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                graphicsResponse.setStatsCycle(response);
            } else {
                graphicsResponse.setCode(DAConstantListener.CODE_NO_DATA);
                graphicsResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("DAGraphicsApi/DADownloadFilesApi No data found.");
            }
            return Response.ok(graphicsResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            graphicsResponse.setCode(DAConstantListener.CODE_ERROR);
            graphicsResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(graphicsResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DASelectDeleteMPApi/{cycle}/{bank}/{error}")
    @Log(value = "Bank", valueMethod = "/DASelectDeleteMPApi/{cycle}/{bank}/{error}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.CHECK_ERRORS_200, response = SelectDeleteMpResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.CHECK_ERRORS_VALUE, response = SelectDeleteMpResponse.class)
    public Response daSelectDeleteMPApi(@ApiParam(name = "cycle", example = "20220505", required = true, value = "Numeric type") @PathParam("cycle") String cycle, @ApiParam(name = "Bank", example = "Bac", required = true, value = "String value") @PathParam("bank") String bank, @ApiParam(name = "error", example = "02", required = true, value = "String value") @PathParam("error") String error) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<DeleteMpDTO> response = null;
        SelectDeleteMpResponse selectDeleteMpResponse = new SelectDeleteMpResponse();
        try {
            try {
                this.validateParam.validatedSelectDeleteMP(cycle, bank, error);
            } catch (GenericException e) {
                selectDeleteMpResponse.setCode(DAConstantListener.CODE_ERROR);
                selectDeleteMpResponse.setDescription(e.getMessage());
                return Response.ok(selectDeleteMpResponse).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            List<TwoLastCycleDTO> dtoCycles = manager.getTwoLastCycle(cycle, bank);
            String inIdCycle = "";
            if (dtoCycles.size() == 2) {
                inIdCycle = "'" + dtoCycles.get(0).getIdCycle() + "','" + dtoCycles.get(1).getIdCycle() + "'";
            } else {
                inIdCycle = "''";
            }
            response = manager.getSelectDeleteMp(bank, cycle, error, inIdCycle);
            if (!response.isEmpty()) {
                selectDeleteMpResponse.setCode(DAConstantListener.CODE_SUCCESS);
                selectDeleteMpResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                selectDeleteMpResponse.setSeletectDeleteMp(response);
            } else {
                selectDeleteMpResponse.setCode(DAConstantListener.CODE_NO_DATA);
                selectDeleteMpResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("/DASelectDeleteMPApi No data found.");
            }
            return Response.ok(selectDeleteMpResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            selectDeleteMpResponse.setCode(DAConstantListener.CODE_ERROR);
            selectDeleteMpResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(selectDeleteMpResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DASelectDeleteMPDetailApi/{cycle}/{bank}/{error}/{account}")
    @Log(value = "Bank", valueMethod = "/DASelectDeleteMPDetailApi/{cycle}/{bank}/{error}/{account}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.CHECK_ERRORS_DETAILS_200, response = SelectDeleteMpResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.CHECK_ERRORS_DETAILS_VALUE, response = SelectDeleteMpResponse.class)
    public Response daSelectDeleteMPDetailApi(@ApiParam(name = "cycle", example = "20220505", required = true, value = "Numeric type") @PathParam("cycle") String cycle, @ApiParam(name = "bank", example = "Bac", required = true, value = "String value") @PathParam("bank") String bank, @ApiParam(name = "error", example = "02", required = true, value = "String value") @PathParam("error") String error, @ApiParam(name = "account", example = "9000247760", required = true, value = "String value") @PathParam("account") String account) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<DeleteMpDTO> response = null;
        SelectDeleteMpResponse selectDeleteMpResponse = new SelectDeleteMpResponse();
        try {
            try {
                this.validateParam.validatedSelectDeleteMP(cycle, bank, error);
            } catch (GenericException e) {
                selectDeleteMpResponse.setCode(DAConstantListener.CODE_ERROR);
                selectDeleteMpResponse.setDescription(e.getMessage());
                return Response.ok(selectDeleteMpResponse).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            List<TwoLastCycleDTO> dtoCycles = manager.getTwoLastCycle(cycle, bank);
            String inIdCycle = "";
            if (dtoCycles.size() == 2) {
                inIdCycle = "'" + dtoCycles.get(0).getIdCycle() + "','" + dtoCycles.get(1).getIdCycle() + "'";
            }
            response = manager.getSelectDeleteDetailMp(bank, cycle, error, inIdCycle, account);
            if (!response.isEmpty()) {
                selectDeleteMpResponse.setCode(DAConstantListener.CODE_SUCCESS);
                selectDeleteMpResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                selectDeleteMpResponse.setSeletectDeleteMp(response);
            } else {
                selectDeleteMpResponse.setCode(DAConstantListener.CODE_NO_DATA);
                selectDeleteMpResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("/DASelectDeleteMPDetailApi No data found.");
            }
            return Response.ok(selectDeleteMpResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            selectDeleteMpResponse.setCode(DAConstantListener.CODE_ERROR);
            selectDeleteMpResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(selectDeleteMpResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DASelectDeleteMPDetailReportApi/{cycle}/{bank}")
    @Log(value = "Bank", valueMethod = "/DASelectDeleteMPDetailReportApi/{cycle}/{bank}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.CHECK_ERRORS_DETAILS_200, response = SelectDeleteMpResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.CHECK_ERRORS_DETAILS_VALUE, response = SelectDeleteMpResponse.class)
    public Response daSelectDeleteMPDetailReportApi(@ApiParam(name = "cycle", example = "20220505", required = true, value = "Numeric type") @PathParam("cycle") String cycle, @ApiParam(name = "bank", example = "Bac", required = true, value = "String value") @PathParam("bank") String bank) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<CardInfoDTO> response = null;
        ReportMpResponse reportMpResponse = new ReportMpResponse();
        try {
            try {
                this.validateParam.validatedSelectDeleteMP(cycle, bank, "1");
            } catch (GenericException e) {
                reportMpResponse.setCode(DAConstantListener.CODE_ERROR);
                reportMpResponse.setDescription(e.getMessage());
                return Response.ok(reportMpResponse).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getCarInfoDetailReport(bank, cycle);
            if (!response.isEmpty()) {
                reportMpResponse.setCode(DAConstantListener.CODE_SUCCESS);
                reportMpResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                reportMpResponse.setCardInfoReport(response);
            } else {
                reportMpResponse.setCode(DAConstantListener.CODE_NO_DATA);
                reportMpResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("/DASelectDeleteMPDetailReportApi No data found.");
            }
            return Response.ok(reportMpResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            reportMpResponse.setCode(DAConstantListener.CODE_ERROR);
            reportMpResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(reportMpResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DAFindFileName/{fileName}/{idBank}")
    @Log(value = "Bank", valueMethod = "/DAFindFileName/{fileName}/{idBank}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.FIND_FILE_200, response = FileNameResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.FIND_FILE_VALUE, response = FileNameResponse.class)
    public Response daFindFileName(@ApiParam(name = "fileName", example = "Bac_20220605", required = true, value = "Alfanumeric type") @PathParam("fileName") String fileName, @ApiParam(name = "idBank", example = "1000", required = true, value = "Numeric type") @PathParam("idBank") String idBank) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<TwoLastCycleDTO> response = null;
        FileNameResponse fileNameResponse = new FileNameResponse();
        try {
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            String[] arr = fileName.split("_");
            // EXISTE ESTE CICLO PARA BANK PROCESS
            List<BankProcessDTO> responseBank = manager.getReprocessBankByCycle(arr[1], idBank);
            if (!responseBank.isEmpty()) {
                response = manager.getOneLastCycle(arr[1], arr[0], idBank);
                if (!response.isEmpty()) {
                    if (response.get(0).getFileName() != null) {
                        int num = Integer.parseInt(response.get(0).getFileName().substring(13, 17));
                        String fileN = arr[0] + "_" + arr[1] + "_" + String.format("%4s", (num + 1)).replace(' ', '0');
                        fileNameResponse.setCode(DAConstantListener.CODE_SUCCESS);
                        fileNameResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                        fileNameResponse.setFileName(fileN);
                    } else {
                        fileNameResponse.setCode(DAConstantListener.CODE_SUCCESS);
                        fileNameResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                        fileNameResponse.setFileName(arr[0] + "_" + arr[1] + "_0001");
                    }
                } else {
                    fileNameResponse.setCode(DAConstantListener.CODE_SUCCESS);
                    fileNameResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                    fileNameResponse.setFileName(arr[0] + "_" + arr[1] + "_0001");
                }
            } else {
                fileNameResponse.setCode(DAConstantListener.CODE_NO_DATA);
                fileNameResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("/DAFindFileName No data found.");
            }
            return Response.ok(fileNameResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            fileNameResponse.setCode(DAConstantListener.CODE_ERROR);
            fileNameResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(fileNameResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DAVerifyPaymentMethod/{acctcode}")
    @Log(value = "Bank", valueMethod = "/DAVerifyPaymentMethod/{acctcode}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.PAYMENT_METHOD_200, response = GraphicsResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.PAYMENT_METHOD_VALUE, response = GraphicsResponse.class)
    public Response daVerifyPaymentMethod(@ApiParam(name = "acctcode", example = "9000247760", required = true, value = "Numeric type") @PathParam("acctcode") String acctcode) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        CardInfoDTO response = null;
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.existPaymemtMethod(acctcode);
            if (response != null) {
                generalResponse.setCode(DAConstantListener.CODE_SUCCESS);
                generalResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
            } else {
                generalResponse.setCode(DAConstantListener.CODE_NO_DATA);
                generalResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("/DAVerifyPaymentMethod No data found.");
            }
            return Response.ok(generalResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            generalResponse.setCode(DAConstantListener.CODE_ERROR);
            generalResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(generalResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DAgetAllDetailBankProcess/{id}/{type}")
    @Log(value = "Bank", valueMethod = "/DAgetAllDetailBankProcess/{id}/{type}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_GRAPH_200, response = AllDetailResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_GRAPH_VALUE, response = AllDetailResponse.class)
    public Response dAgetAllDetailBankProcess(@ApiParam(name = "type", example = "all", required = true, value = "String value") @PathParam("type") String type, @ApiParam(name = "id", example = "fsdy44-5345-2356-fdfsHdsd8-32423", required = true, value = "String value") @PathParam("id") String id) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        ArrayList<String> response = new ArrayList<>();
        AllDetailResponse allDetailResponse = new AllDetailResponse();
        try {
            try {
                this.validateParam.validatedIdProcessBank(id);
            } catch (GenericException e) {
                allDetailResponse.setCode(DAConstantListener.CODE_ERROR);
                allDetailResponse.setDescription(e.getMessage());
                return Response.ok(allDetailResponse).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.AllDetailBankProcessEntity(id, type);
            if (response != null) {
                allDetailResponse.setCode(DAConstantListener.CODE_SUCCESS);
                allDetailResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                allDetailResponse.setAllDetailBankProcess(response);
            } else {
                allDetailResponse.setCode(DAConstantListener.CODE_NO_DATA);
                allDetailResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("DAgetAllDetailBankProcess No data found.");
            }
            return Response.ok(allDetailResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            allDetailResponse.setCode(DAConstantListener.CODE_ERROR);
            allDetailResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(allDetailResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DASelectProcessByBankCycle/{type}/{idBank}/{cycle}")
    @Log(value = "Bank", valueMethod = "/DASelectProcessByBankCycle/{type}/{idBank}/{cycle}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_INFORMATION_BEFORE_PROCESSING_200, response = BankProcessResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_INFORMATION_BEFORE_PROCESSING_VALUE, response = BankProcessResponse.class)
    public Response daSelectProcessByBankCycle(@ApiParam(name = "type", example = "1", required = true, value = "Integer value") @PathParam("type") String type, @ApiParam(name = "idBank", example = "1000", required = true, value = "Integer value") @PathParam("idBank") String idBank, @ApiParam(name = "cycle", example = "04", required = true, value = "String value") @PathParam("cycle") String cycle) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<BankProcessDTO> response = null;
        BankProcessResponse bankProcessResponse = new BankProcessResponse();
        try {
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getBankProcessByBankCycle(idBank, cycle, type);
            if (response.isEmpty()) {
                bankProcessResponse.setCode(DAConstantListener.CODE_NO_DATA);
                bankProcessResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DASelectProcessByBankCycle No data found.");
            } else {
                bankProcessResponse.setCode(DAConstantListener.CODE_SUCCESS);
                bankProcessResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                bankProcessResponse.setBankProcessCycles(response);
            }
            return Response.ok(bankProcessResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            bankProcessResponse.setCode(DAConstantListener.CODE_ERROR);
            bankProcessResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(bankProcessResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DASelectLogsByType/{type}/{dateIn}/{dateEnd}")
    @Log(value = "Bank", valueMethod = "/DASelectLogsByType/{type}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_INFORMATION_BEFORE_PROCESSING_200, response = BankProcessResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_INFORMATION_BEFORE_PROCESSING_VALUE, response = BankProcessResponse.class)
    public Response daSelectLogsByType(@ApiParam(name = "type", example = "1", required = true, value = "Integer value") @PathParam("type") String type, @ApiParam(name = "dateIn", example = "2022-07-04", required = true, value = "Format value YYYY-MM-DD") @PathParam("dateIn") String dateIn, @ApiParam(name = "dateEnd", example = "2022-07-10", required = true, value = "Format value YYYY-MM-DD") @PathParam("dateEnd") String dateEnd) {
        DAManager manager = null;
        ArrayList<String> response = new ArrayList<>();
        LogErrorResponse logErrorResponse = new LogErrorResponse();
        try {
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getLogErrorByType(type, dateIn, dateEnd);
            if (response.isEmpty()) {
                logErrorResponse.setCode(DAConstantListener.CODE_NO_DATA);
                logErrorResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DASelectLogsByType No data found.");
            } else {
                logErrorResponse.setCode(DAConstantListener.CODE_SUCCESS);
                logErrorResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                logErrorResponse.setLogError(response);
            }
            return Response.ok(logErrorResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            logErrorResponse.setCode(DAConstantListener.CODE_ERROR);
            logErrorResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(logErrorResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/DASyncAccount/{account}")
    @Log(value = "Bank", valueMethod = "/DASyncAccount/{account}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_INFORMATION_BEFORE_PROCESSING_200, response = BankProcessResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_INFORMATION_BEFORE_PROCESSING_VALUE, response = BankProcessResponse.class)
    public Response dASyncAccount(@ApiParam(name = "account", example = "7984233562", required = true, value = "String value") @PathParam("account") String account) {
        DAManager manager = null;
        SyncAccountResponse syncAccountResponse = new SyncAccountResponse();
        try {
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, DAListenerConstants.JNDI);
            configParams = manager.listAllParam();
            CBSQueryCustomerInfoServiceProvider service = new CBSQueryCustomerInfoServiceProvider(configParams.get(DAListenerConstants.WSDL_CBS_INVOICE_DA));
            ParameterType parametro = new ParameterType();
            ParameterArray parameterArray = new ParameterArray();
            ArrayList<ParameterType> listParametro = new ArrayList<>();
            parametro.setName("JSON");
            parametro.setValue("{\"queryObj\":{\"acctAccessCode\":{\"accountCode\":\"accountCode_replace\",\"payType\":\"2\"}}}".replace("accountCode_replace", account));
            listParametro.add(0, parametro);
            parameterArray.setParameter(listParametro);
            TaskRequestType taskRequestType = new TaskRequestType();
            taskRequestType.setParameters(parameterArray);
            TaskResponseType responseService = service.excuteTask(taskRequestType);
            String jsonService = ((ParameterType) responseService.getParameters().getParameter().get(0)).getValue();

            JSONObject jsonObject = new JSONObject(jsonService);
            JSONObject envResponse = jsonObject.getJSONObject("QueryCustomerInfoResultMsg");
            JSONObject queryCustomerInfoResult = envResponse.getJSONObject("QueryCustomerInfoResult");
            JSONObject account_ = queryCustomerInfoResult.getJSONObject("Account");
            JSONObject acctInfo = account_.getJSONObject("AcctInfo");
            JSONObject acctBasicInfo = acctInfo.getJSONObject("AcctBasicInfo");
            JSONArray AcctProperty = acctBasicInfo.getJSONArray("AcctProperty");
            String accountInfo3 = "";
            for (int i = 0; i < AcctProperty.length(); i++) {
                JSONObject object = AcctProperty.getJSONObject(i);
                if ("C_ACCOUNT_INFO_3".equalsIgnoreCase(object.getString("Code"))) {
                    accountInfo3 = object.get("Value").toString();
                }
            }
            if (!"".equals(accountInfo3)) {
                // ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
                //manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
                manager.UpdatePrimaryMP(accountInfo3, account);
                syncAccountResponse.setCode(DAConstantListener.CODE_SUCCESS);
                syncAccountResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                syncAccountResponse.setPrimaryIdentity(accountInfo3);
            } else {
                syncAccountResponse.setCode(DAConstantListener.CODE_NO_DATA);
                syncAccountResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DASyncAccount No data found.");
            }
            return Response.ok(syncAccountResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            syncAccountResponse.setCode(DAConstantListener.CODE_ERROR);
            syncAccountResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(syncAccountResponse).build();
        } catch (Exception e) {
            syncAccountResponse.setCode(DAConstantListener.CODE_ERROR);
            syncAccountResponse.setDescription(e.getMessage());
            LOGGER.error(e.getMessage());
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(syncAccountResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @GET
    @Path("/encrypt/{param}")
    @ApiOperation(value = "", hidden = true)
    @Log(value = "Bank", valueMethod = "/encrypt/{param}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    public String encrypt(@PathParam("param") String param) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return EncryptDecrypt.encript(param);
    }

    @GET
    @Path("/decrypt/{param}")
    @ApiOperation(value = "", hidden = true)
    @Log(value = "Bank", valueMethod = "/decrypt/{param}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    public String decrypt(@PathParam("param") String param) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] decodedBytes = Base64.getDecoder().decode(param);
        String decodedString = new String(decodedBytes);
        return EncryptDecrypt.decript(decodedString);
    }

    @POST
    @Path("/DAAproversApi")
    @Log(value = Constants.DATASOURCE, valueMethod = "/DAAproversApi", project = "InvoiceUtilsApis")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_APPROVE_REQUEST_200, response = BankProcessResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_APPROVE_REQUEST_VALUE, response = BankProcessResponse.class)
    public Response daAproversApi(final ApproverModel request) {
        DAManager manager = null;
        final GeneralResponse responsePostMethod = new GeneralResponse();
        try {
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");

            if ("1".equalsIgnoreCase(request.getTypeAprobacion())) {
                // GENERAR SOLICITUD
                manager.insertAprobacionCiclo(request.getIdProcess(), request.getType(), request.getUser(), request.getComment());
                // CAMBIAR ESTATUS DEL PROCESO
                if ("20".equalsIgnoreCase(String.valueOf(request.getType()))) {
                    BankReProcessDTO dtoBank = new BankReProcessDTO();
                    dtoBank.setId(request.getIdProcess());
                    dtoBank.setStatus(20L);
                    manager.updateBankProcessByStatus(dtoBank);
                } else if ("21".equalsIgnoreCase(String.valueOf(request.getType()))) {
                    BankReProcessDTO dtoBank = new BankReProcessDTO();
                    dtoBank.setId(request.getIdProcess());
                    dtoBank.setStatus(21L);
                    manager.updateBankProcessByStatus(dtoBank);
                }
            } else {
                // APROBAR SOLICITUD
                manager.updateAprobacionCiclo(request.getIdProcess(), String.valueOf(request.getType()), request.getUser(), request.getComment());
                BankReProcessDTO dtoBank = new BankReProcessDTO();
                dtoBank.setId(request.getIdProcess());
                if ("20".equalsIgnoreCase(String.valueOf(request.getType()))) {
                    dtoBank.setStatus(10);
                } else if ("21".equalsIgnoreCase(String.valueOf(request.getType()))) {
                    dtoBank.setStatus(5);
                }
                manager.updateBankProcessByStatus(dtoBank);
            }

            responsePostMethod.setCode(DAConstantListener.CODE_SUCCESS);
            responsePostMethod.setDescription(DAConstantListener.MESSAGE_SUCESS);
            return Response.ok(responsePostMethod).cacheControl(new CacheControl()).type(MediaType.APPLICATION_JSON).build();

        } catch (PersistenceException e) {
            responsePostMethod.setCode(DAConstantListener.CODE_NO_DATA);
            responsePostMethod.setDescription(e.getMessage());
            NewRelicImpl.addNewRelicError(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).type(MediaType.APPLICATION_JSON).entity(responsePostMethod).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @POST
    @Path("/DACreateMPApi")
    @Log(value = "Bank", valueMethod = "/DACreateMPApi", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.CREATE_MP_200, response = GeneralResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.CREATE_MP_VALUE, response = GeneralResponse.class)
    public Response dACreateMPApi(final CardInfoSwaggerModel request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        GeneralResponse response = new GeneralResponse();
        try {
            try {
               // if (!validateCard.validarTarjeta(request.getNoCard())) {
                 //   response.setCode(DAConstantListener.CODE_ERROR);
                   // response.setDescription("La tarjeta ingresada no es valida");
                //    return Response.ok(response).cacheControl(new CacheControl()).build();
                //}
                this.validateParam.validatedCreateMPApi(request.getName(), request.getNoCard(), request.getTypeCard(), request.getModalityCard(),
                        request.getYear(), request.getMonth(), request.getIssuingBank(), request.getProcessorBank(), request.getGroupPayment(), request.getCycle(), request.getStatus(), request.getAcctCode(), request.getSubscriberId());
            } catch (GenericException e) {
                response.setCode(DAConstantListener.CODE_ERROR);
                response.setDescription(e.getMessage());
                return Response.ok(response).cacheControl(new CacheControl()).build();
            }

            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            manager.insertCardInfo(request);
            response.setCode(DAConstantListener.CODE_SUCCESS);
            response.setDescription(DAConstantListener.MESSAGE_SUCESS);
            return Response.ok(response).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            response.setCode(DAConstantListener.CODE_ERROR);
            response.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(response).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException ex) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @POST
    @Path("/DAUpdateMPApi")
    @Log(value = "Bank", valueMethod = "/DAUpdateMPApi", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.UPDATE_MP_200, response = GeneralResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.UPDATE_MP_VALUE, response = GeneralResponse.class)
    public Response dAUpdateMPApi(final CardInfoSwaggerModel request) throws PersistenceException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        DAManager manager = null;
        GeneralResponse response = new GeneralResponse();
        try {
            try {
               // if (!validateCard.validarTarjeta(request.getNoCard())) {
                 //   response.setCode(DAConstantListener.CODE_ERROR);
                   // response.setDescription("La tarjeta ingresada no es valida");
                   // return Response.ok(response).cacheControl(new CacheControl()).build();
                //}
                this.validateParam.validatedUpdateMPApi(request.getId());
            } catch (GenericException e) {
                response.setCode(DAConstantListener.CODE_ERROR);
                response.setDescription(e.getMessage());
                return Response.ok(response).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            manager.updateCardInfo(request);
            response.setCode(DAConstantListener.CODE_SUCCESS);
            response.setDescription(DAConstantListener.MESSAGE_SUCESS);
            return Response.ok(response).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            response.setCode(DAConstantListener.CODE_ERROR);
            response.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(response).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException ex) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @POST
    @Path("/DADeleteMPApi")
    @Log(value = "Bank", valueMethod = "/DADeleteMPApi", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.DELETE_MP_200, response = GeneralResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.DELETE_MP_VALUE, response = GeneralResponse.class)
    public Response dADeleteMPApi(final DeleteListModel request) {
        DAManager manager = null;
        ResponseDeleteAccount response = new ResponseDeleteAccount();
        try {
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            String uuid = UUID.randomUUID().toString();
            final Gson json = new Gson();
            manager.insertBankDeleteStatus(uuid, json.toJson(request));
            response.setCode(DAConstantListener.CODE_SUCCESS);
            response.setDescription(DAConstantListener.MESSAGE_SUCESS);
            response.setUuid(uuid);
            return Response.ok(response).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            response.setCode(DAConstantListener.CODE_ERROR);
            response.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(response).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException ex) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @POST
    @Path("/DACreateIncluExcluApi")
    @Log(value = "Bank", valueMethod = "/DACreateIncluExcluApi", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.CREATE_INCLU_AND_EXCLU_200, response = GeneralResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.CREATE_INCLU_AND_EXCLU_VALUE, response = GeneralResponse.class)
    public Response dACreateIncluExcluApi(final IncluAndExcluDTO request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        GeneralResponse response = new GeneralResponse();
        try {
            try {
                this.validateParam.validatedCreateIncluExcluApi(request.getId(), request.getNumpla(), request.getType_tran(), request.getAnexo(), request.getNoCard(), request.getCardIsueDate(), request.getExpDate(), String.valueOf(request.getStatus()), request.getCycle());
            } catch (GenericException e) {
                response.setCode(DAConstantListener.CODE_ERROR);
                response.setDescription(e.getMessage());
                return Response.ok(response).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            manager.insertBankIncluAndExclu(request);
            response.setCode(DAConstantListener.CODE_SUCCESS);
            response.setDescription(DAConstantListener.MESSAGE_SUCESS);
            return Response.ok(response).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            response.setCode(DAConstantListener.CODE_ERROR);
            response.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(response).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException ex) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @POST
    @Path("/DACreateProcessCycleApi")
    @Log(value = "Bank", valueMethod = "/DACreateProcessCycleApi", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_UPDATE_CYCLE_200, response = GeneralResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.CREATE_INCLU_AND_EXCLU_VALUE, response = GeneralResponse.class)
    public Response daCreateProcessCycleApi(final BankProcessModel request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        GeneralResponse response = new GeneralResponse();
        try {
            try {
                this.validateParam.validatedInsertProcessBank(String.valueOf(request.getBankId()), String.valueOf(request.getCycle()), request.getUser());
            } catch (GenericException e) {
                response.setCode(DAConstantListener.CODE_ERROR);
                response.setDescription(e.getMessage());
                return Response.ok(response).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            
            List<BankProcessDTO> bankProcess = manager.getBankProcessByBankId(request.getBankId(), request.getCycle());
            
			if (bankProcess != null && bankProcess.size() > 0) {

				if (bankProcess.get(0).getStatus() < 4) {
					response.setCode(DAConstantListener.CODE_ERROR);
					response.setDescription(DAConstantListener.MESSAGE_PROCESS_CYCLE);
					NewRelicImpl.addNewRelicErrorMessage("ApisDA/DACreateProcessCycleApi Ciclo pendiente de procesar.");
				} else {

					insertToBankProcess(request, manager, response);
				}

			} else {

				insertToBankProcess(request, manager, response);
			}
            return Response.ok(response).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            response.setCode(DAConstantListener.CODE_ERROR);
            response.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(response).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException ex) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

	private void insertToBankProcess(final BankProcessModel request, DAManager manager, GeneralResponse response)
			throws PersistenceException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		String uuid = UUID.randomUUID().toString();
		manager.insertBankProcess(request, uuid);
		response.setCode(DAConstantListener.CODE_SUCCESS);
		response.setDescription(DAConstantListener.MESSAGE_SUCESS);
	}

    @POST
    @Path("/DAReprocessCycleApi")
    @Log(value = "Bank", valueMethod = "/DAReprocessCycleApi", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.CREATE_REPROCESS_200, response = GeneralResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.CREATE_REPROCESS_VALUE, response = GeneralResponse.class)
    public Response daReprocessCycleApi(final BankReProcessDTO request) {
        DAManager manager = null;
        GeneralResponse response = new GeneralResponse();
        try {
            try {
                this.validateParam.validatedUpdateProcessBank(request.getId(), String.valueOf(request.getStatus()));
            } catch (GenericException e) {
                response.setCode(DAConstantListener.CODE_ERROR);
                response.setDescription(e.getMessage());
                return Response.ok(response).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            manager.deleteProcessDetail(request.getId());
            manager.updateBankProcessByStatus(request);
            response.setCode(DAConstantListener.CODE_SUCCESS);
            response.setDescription(DAConstantListener.MESSAGE_SUCESS);
            return Response.ok(response).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            response.setCode(DAConstantListener.CODE_ERROR);
            response.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(response).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException ex) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @POST
    @Path("/DAChangeProcessCycleApi")
    @Log(value = "Bank", valueMethod = "/DAChangeProcessCycleApi", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.GET_UPDATE_CYCLE_200, response = GeneralResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.GET_UPDATE_CYCLE_VALUE, response = GeneralResponse.class)
    public Response daChangeProcessCycleApi(final BankReProcessDTO request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        GeneralResponse response = new GeneralResponse();
        try {
            try {
                this.validateParam.validatedUpdateProcessBank(request.getId(), String.valueOf(request.getStatus()));
            } catch (GenericException e) {
                response.setCode(DAConstantListener.CODE_ERROR);
                response.setDescription(e.getMessage());
                return Response.ok(response).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            if (request.getStatus() == 3) {
                // VERIFICAR SI ESTA SUBIENDO EL ARCHIVO
                if (("".equals(request.getFileNameBank()) || request.getFileNameBank() == null) && ("".equals(request.getBase64Bank()) || request.getBase64Bank() == null)) {
                    response.setCode(DAConstantListener.CODE_ERROR);
                    response.setDescription(DAConstantListener.NOT_FILE);
                    return Response.ok(response).cacheControl(new CacheControl()).build();
                }
            }
            manager.updateBankProcessByStatus(request);
            response.setCode(DAConstantListener.CODE_SUCCESS);
            response.setDescription(DAConstantListener.MESSAGE_SUCESS);
            return Response.ok(response).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            response.setCode(DAConstantListener.CODE_ERROR);
            response.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(response).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException ex) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    @POST
    @Path("/DAReProcessClientsApi")
    @Log(value = "Bank", valueMethod = "/DAReProcessClientsApi", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = DASwaggerConstant.CREATE_INDIVIDUAL_REPROCESS_FILE_200, response = GeneralResponse.class)
        ,
    @ApiResponse(code = 400, message = DASwaggerConstant.ERROR_400_MESSAGE)
        ,
    @ApiResponse(code = 401, message = DASwaggerConstant.ERROR_401_MESSAGE)
        ,
    @ApiResponse(code = 404, message = DASwaggerConstant.ERROR_404_MESSAGE)
        ,
    @ApiResponse(code = 500, message = DASwaggerConstant.ERROR_500_MESSAGE)})
    @ApiOperation(value = DASwaggerConstant.CREATE_INDIVIDUAL_REPROCESS_FILE_VALUE, response = GeneralResponse.class)
    public Response daReProcessClientsApi(final ReprocessFileDTO request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException, InvalidFormatException {
        DAManager manager = null;
        GeneralResponse response = new GeneralResponse();
        try {
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            String uuid = UUID.randomUUID().toString();
            BankProcessModel dto = new BankProcessModel();

            List<List<String>> globalLista = ExcelDecode.decodeExcel(request.getFileB64(), request.getNumRegister());
            CardInfoDTO responseExist = null;
            for (List<String> row : globalLista) {
                DetailReprocessDTO dtoDetail = new DetailReprocessDTO();
                responseExist = manager.existPaymemtMethod(row.get(1));
                if (responseExist != null) {
                    dtoDetail.setClient(row.get(0));
                    dtoDetail.setAccount(row.get(1));
                    dtoDetail.setSubs(row.get(2));
                    dtoDetail.setCycle(row.get(3));
                    dtoDetail.setIdProcess(uuid);
                    manager.insertBankReProcessDetail(dtoDetail, uuid);
                }
            }
            dto.setCycle(request.getCycle());
            dto.setUser(request.getUser());
            long bankId = 1001;
            if (request.getFileName().contains("Bac")) {
                bankId = 1000;
            }
            dto.setBankId(bankId);
            manager.insertReprocess(request, uuid);
            manager.insertBankReProcess(dto, uuid);

            response.setCode(DAConstantListener.CODE_SUCCESS);
            response.setDescription(DAConstantListener.MESSAGE_SUCESS);
            return Response.ok(response).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            response.setCode(DAConstantListener.CODE_ERROR);
            response.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(response).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException ex) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }

    private void sendMessage(String correosAprobadores, final String typeAprobacion, final String idProcess, final String cycle, final String user, final String comment) throws MalformedURLException {
        EmailServiceProvider serviceEmail = null;
        try {
            serviceEmail = new EmailServiceProvider("http://192.168.185.90:7006/EmailService/EmailService");
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            SentDTO sendTo = new SentDTO();
            String[] emails = correosAprobadores.split(";");
            for (String ml : emails) {
                ToDto email = new ToDto();
                email.setTo(ml);
                sendTo.getSend().add(email);
            }
            if ("20".equalsIgnoreCase(typeAprobacion)) {
                serviceEmail.sendMessage("Solicitud de Reproceso Archivo de salida DA.", sendTo, "", "Solicitud de Reproceso Archivo de salida DA.", "<h4 style=\"color: #003b8d;\">Se ha realizado la solicitud de Reproceso Archivo de salida DA, a continuaci&oacute;n los detalles:</h4><ul><li>Ciclo:&nbsp;" + cycle + "</li><li>Usuario Solicitante:&nbsp;" + user + "</li><li>Fecha Solicitud:&nbsp;" + df.format(Calendar.getInstance().getTime()) + "</li><li>Comentario:&nbsp;" + comment + "</li><li>Sistema:&nbsp;Desarrollo</li></ul>", null);
            }
        } catch (MalformedURLException ex) {
            throw new MalformedURLException();
        }
    }

    @GET
    @Path("/DASelectDetailsReprocess/{id}")
    @ApiOperation(value = "", hidden = true)
    @Log(value = "Bank", valueMethod = "/DASelectDetailsReprocess/{id}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dASelectDetailsReprocess(@PathParam("id") String id) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<DetailsReprocessDTO> response = null;
        DetailsReprocessResponse detailsResponse = new DetailsReprocessResponse();
        try {
            try {
                this.validateParam.validatedSelectDetailsReprocess(id);
            } catch (GenericException e) {
                detailsResponse.setCode(DAConstantListener.CODE_ERROR);
                detailsResponse.setDescription(e.getMessage());
                return Response.ok(detailsResponse).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            response = manager.getDetailsReprocessByIdProcess(id);
            if (response.isEmpty()) {
                detailsResponse.setCode(DAConstantListener.CODE_NO_DATA);
                detailsResponse.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DASelectDetailsReprocess No data found.");
                detailsResponse.setDetailsReprocess(response);
            } else {
                detailsResponse.setCode(DAConstantListener.CODE_SUCCESS);
                detailsResponse.setDescription(DAConstantListener.MESSAGE_SUCESS);
                detailsResponse.setDetailsReprocess(response);
            }
            return Response.ok(detailsResponse).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
            detailsResponse.setCode(DAConstantListener.CODE_ERROR);
            detailsResponse.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(detailsResponse).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }
    
    
    @GET
    @Path("/DASelectApplyPayError/{id}")
    @ApiOperation(value = "", hidden = true)
    @Log(value = "Bank", valueMethod = "/DASelectApplyPayError/{id}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dASelectApplyPayError(@PathParam("id") String id) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<DABankProcessDetailPayDTO> list = null;
        ApplyPayResponse response = new ApplyPayResponse();
        try {
            try {
                this.validateParam.validatedIdProcessBank(id);
            } catch (GenericException e) {
            	response.setCode(DAConstantListener.CODE_ERROR);
            	response.setDescription(e.getMessage());
                return Response.ok(response).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            list = manager.selectApplyPayError(id, -1, "00");
            if (list == null || list.size()==0) {
            	response.setCode(DAConstantListener.CODE_NO_DATA);
            	response.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DASelectApplyPayError No data found.");
            } else {
            	response.setCode(DAConstantListener.CODE_SUCCESS);
            	response.setDescription(DAConstantListener.MESSAGE_SUCESS);
            	response.setBankProccesDetailPay(list);
            }
            return Response.ok(response).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
        	response.setCode(DAConstantListener.CODE_ERROR);
        	response.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(response).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }
    
    @GET
    @Path("/DAApplyPayError/{id}")
    @ApiOperation(value = "", hidden = true)
    @Log(value = "Bank", valueMethod = "/DAApplyPayError/{id}", project = "ApisDA")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dAApplyPayError(@PathParam("id") String id) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        DAManager manager = null;
        List<DABankProcessDetailPayDTO> list = null;
        GeneralResponse response = new GeneralResponse();
        try {
            try {
                this.validateParam.validatedIdProcessBank(id);
            } catch (GenericException e) {
            	response.setCode(DAConstantListener.CODE_ERROR);
            	response.setDescription(e.getMessage());
                return Response.ok(response).cacheControl(new CacheControl()).build();
            }
            ServiceSessionEJBLocal<DAManager> serviceSession = ServiceSessionEJB.getInstance();
            manager = (DAManager) serviceSession.getSessionDataSource(DAManager.class, "Bank");
            configParams = manager.listAllParam();
            list = manager.selectApplyPayError(id, -1, "00");
            
            if (list == null || list.size()==0) {
            	response.setCode(DAConstantListener.CODE_NO_DATA);
            	response.setDescription(DAConstantListener.MESSAGE_NO_DATA);
                NewRelicImpl.addNewRelicErrorMessage("ApisDA/DAApplyPayError No data found.");
            } else {
            	Calendar cycleCalendar = Calendar.getInstance();
				final SimpleDateFormat df = new SimpleDateFormat(
						configParams.get(DAListenerConstants.FORMAT_DATE_YYYYMMDD));
				
            	applyPayError(manager, cycleCalendar, df, id, list);
            	response.setCode(DAConstantListener.CODE_SUCCESS);
                response.setDescription(DAConstantListener.MESSAGE_SUCESS);
            }
            return Response.ok(response).cacheControl(new CacheControl()).build();
        } catch (PersistenceException e) {
        	response.setCode(DAConstantListener.CODE_ERROR);
        	response.setDescription(e.getMessage());
            LOGGER.error(DAConstantListener.MESSAGE_ERROR_DB);
            NewRelicImpl.addNewRelicError((Throwable) e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).cacheControl(new CacheControl()).entity(response).build();
        } finally {
            if (manager != null) {
                try {
                    manager.close();
                } catch (PersistenceException e) {
                    LOGGER.error(DAConstantListener.MESSAGE_CLOSE_DB);
                }
            }
        }
    }
    
	private void applyPayError(DAManager manager, Calendar cycleCalendar, final SimpleDateFormat df,
			final String bankProcessId, List<DABankProcessDetailPayDTO> listApplyPayError) throws PersistenceException {

		List<BankProcessDTO> bankProcess = manager.getBankProcessById(bankProcessId);
		configParams = manager.listAllParam();

		long bankId = bankProcess.get(0).getBankId();
		String bankProcessor = "";

		if (bankId == 1000) {
			bankProcessor = configParams.get(DAListenerConstants.BANK_PROC_BAC);
		} else if (bankId == 1001) {
			bankProcessor = configParams.get(DAListenerConstants.BANK_PROC_FICOHSA);
		}

		if (listApplyPayError != null) {
			int g = 0;
			while (g < Long.parseLong(configParams.get(DAListenerConstants.RETRIES))) {

				for (int f = 0; f < listApplyPayError.size(); f++) {

					if (listApplyPayError.get(f) != null) {

						if (listApplyPayError.get(f).getRetries() < Long
								.parseLong(configParams.get(DAListenerConstants.RETRIES))) {

							if (listApplyPayError.get(f).getStatus() == DAListenerConstants.STATUS_NEG1) {

								// servicio order
								URL url = null;
								Order order = null;

								try {
									url = new URL(configParams.get(DAListenerConstants.WSDL_EXOR_COMPLEX));
									order = new ExecuteOrderService(url).getExecuteOrderPort();
								} catch (Exception e1) {
									LOGGER.error(
											"ERROR en aplicacion de pago: Error en creacion de consumo ExecuteOrderService: "
													+ e1.getMessage());
									e1.printStackTrace();
									String uuidEr = UUID.randomUUID().toString();
									manager.insertLogs(uuidEr, DAListenerConstants.TYPE_ERROR1,
											"ERROR en aplicacion de pago: Error en creacion de consumo ExecuteOrderService: "
													+ e1.getMessage(),
											bankProcessId, "");
								}

								// Se obtiene monto por cada registro
								double amount = Double.valueOf(listApplyPayError.get(f).getAmount());

								List<Parameter> parameter = ApiDAUtils.obtainParameters(
										listApplyPayError.get(f).getAcctCode(),
										listApplyPayError.get(f).getSubscriberId(),
										listApplyPayError.get(f).getInvoiceId(), df.format(cycleCalendar.getTime()),
										String.valueOf(amount), configParams.get(bankProcessor),
										configParams.get(DAListenerConstants.PAY_TYPE_EOC_MSG));

								AdditionalParameters additionalParameters = ApiDAUtils
										.getAdditionalParameters(parameter);
								SimpleOrderRequest request = null;
								OrderResponse orderResponse = null;
								String uuid = UUID.randomUUID().toString();
								JSONObject jsonObject = null;

								try {
									// Se hace llamado de servicio ExecuteOrderService

									request = ApiDAUtils.getRequestOrder(listApplyPayError.get(f).getAcctCode(),
											additionalParameters,
											configParams.get(DAListenerConstants.COMMENT_ACTIVATE),
											DAListenerConstants.CHANNEL_ID_98,
											Long.valueOf(configParams.get(DAListenerConstants.PRODUCT_ID)));
									jsonObject = new JSONObject(request);
									orderResponse = order.activateProduct(request);

								} catch (Exception e) {

									LOGGER.error(
											"ERROR en aplicacion de pago: Error en consumo de servicio ExecuteOrderService: "
													+ e.getLocalizedMessage());
									manager.insertLogs(uuid, DAListenerConstants.TYPE_ERROR2,
											"ERROR en aplicacion de pago: Error en consumo de servicio ExecuteOrderService: "
													+ listApplyPayError.get(f).getAcctCode() + " ==> " + e.getMessage(),
											bankProcessId, jsonObject.toString());
								}

								listApplyPayError.get(f).setRetries(listApplyPayError.get(f).getRetries() + 1);

								if (orderResponse != null) {

									String responseCode = orderResponse.getOrderResponseDetail().get(0).getParameters()
											.getParameter().get(0).getValue();

									String responseMessage = orderResponse.getOrderResponseDetail().get(0)
											.getParameters().getParameter().get(1).getValue();

									String responsePayId = orderResponse.getOrderResponseDetail().get(0).getParameters()
											.getParameter().get(2).getValue();

									String responseTXId = orderResponse.getTransactionID();

									listApplyPayError.get(f).setPaymentId(responseTXId);
									listApplyPayError.get(f).setPaymentSeq(responsePayId);

									// Se valida la respuesta de BPMN
									if (!responseCode.equals("0") || responsePayId == "") {

										manager.insertLogs(uuid, DAListenerConstants.TYPE_ERROR2,
												"Se presento un error en la aplicacion de pago, para la cuenta: "
														+ listApplyPayError.get(f).getAcctCode()
														+ " el servicio ExecuteOrderService contesto: "
														+ responseMessage,
												bankProcessId, jsonObject.toString());

									} else {

										manager.insertLogs(uuid, DAListenerConstants.TYPE_ERROR0,
												"Se realizo la aplicacion de pago, para la cuenta: "
														+ listApplyPayError.get(f).getAcctCode() + " "
														+ responseMessage,
												bankProcessId, jsonObject.toString());

										listApplyPayError.get(f).setStatus(DAListenerConstants.STATUS_1);
									}
								}
							}
						}
					}
				}
				g++;
			}

			for (int f = 0; f < listApplyPayError.size(); f++) {

				if (listApplyPayError.get(f) != null) {

					manager.updateBankProcDetailPayByOne(listApplyPayError.get(f).getErrorBank(),
							listApplyPayError.get(f).getStatus(), listApplyPayError.get(f).getPaymentId(),
							listApplyPayError.get(f).getAcctCode(), DAListenerConstants.STATUS_NEG1,
							listApplyPayError.get(f).getGroupPayment(), listApplyPayError.get(f).getInvoiceId(),
							listApplyPayError.get(f).getPaymentSeq(), listApplyPayError.get(f).getRetries(), bankProcessId);
				}
			}
		}
	}
    

}
