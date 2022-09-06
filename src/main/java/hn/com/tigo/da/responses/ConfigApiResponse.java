
package hn.com.tigo.da.responses;

import hn.com.tigo.da.dto.ConfigBanksDTO;
import hn.com.tigo.da.dto.ConfigCyclesDTO;
import hn.com.tigo.da.dto.ConfigErrorDTO;
import hn.com.tigo.da.dto.ConfigStatusDTO;
import hn.com.tigo.da.dto.ConfigYearsDTO;
import hn.com.tigo.da.dto.TwoLastCycleDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class ConfigApiResponse {
    @ApiModelProperty(value = "code", dataType = "String", allowableValues = "string values available", example = "0")
    private String code;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "Success")
    private String description;
    private List<ConfigCyclesDTO> configCycles;
    private List<ConfigBanksDTO> configBanks;
    private List<ConfigYearsDTO> configYears;
    private List<ConfigStatusDTO> configStatus;
    private List<ConfigErrorDTO> configError;
    private List<TwoLastCycleDTO> lastCycleBanks;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ConfigCyclesDTO> getConfigCycles() {
        return configCycles;
    }

    public void setConfigCycles(List<ConfigCyclesDTO> configCycles) {
        this.configCycles = configCycles;
    }

    public List<ConfigBanksDTO> getConfigBanks() {
        return configBanks;
    }

    public void setConfigBanks(List<ConfigBanksDTO> configBanks) {
        this.configBanks = configBanks;
    }

    public List<ConfigYearsDTO> getConfigYears() {
        return configYears;
    }

    public void setConfigYears(List<ConfigYearsDTO> configYears) {
        this.configYears = configYears;
    }

    public List<ConfigStatusDTO> getConfigStatus() {
        return configStatus;
    }

    public void setConfigStatus(List<ConfigStatusDTO> configStatus) {
        this.configStatus = configStatus;
    }

    public List<ConfigErrorDTO> getConfigError() {
        return configError;
    }

    public void setConfigError(List<ConfigErrorDTO> configError) {
        this.configError = configError;
    }

    public List<TwoLastCycleDTO> getLastCycleBanks() {
        return lastCycleBanks;
    }

    public void setLastCycleBanks(List<TwoLastCycleDTO> lastCycleBanks) {
        this.lastCycleBanks = lastCycleBanks;
    }
}
