package com.prison.prisoner.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.prison.common.annotation.Excel;
import com.prison.common.core.domain.BaseEntity;

/**
 * 牢房信息对象 cells
 * 
 * @author che
 * @date 2024-07-01
 */
public class Cells extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 囚犯ID */
    @Excel(name = "囚犯ID")
    private Long cellId;

    /** 监区号 */
    @Excel(name = "监区号")
    private Long blockNumber;

    /** 牢房号 */
    @Excel(name = "牢房号")
    private Long cellNumber;

    public void setCellId(Long cellId) 
    {
        this.cellId = cellId;
    }

    public Long getCellId() 
    {
        return cellId;
    }
    public void setBlockNumber(Long blockNumber) 
    {
        this.blockNumber = blockNumber;
    }

    public Long getBlockNumber() 
    {
        return blockNumber;
    }
    public void setCellNumber(Long cellNumber) 
    {
        this.cellNumber = cellNumber;
    }

    public Long getCellNumber() 
    {
        return cellNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cellId", getCellId())
            .append("blockNumber", getBlockNumber())
            .append("cellNumber", getCellNumber())
            .toString();
    }
}
