package ${package};

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

<#if jakarta>
import jakarta.validation.constraints.*;
<#else>
import javax.validation.constraints.*;
</#if>

/**
 * ${table.remarks}
 *
 * @author ${username}
 */
@Getter
@Setter
@TableName("${table.name}")
public class ${domain} extends AbstractDomain {

    /**
     * ${id.remarks}
     */
    @TableId(type = IdType.AUTO)
    private Long ${id.name};

  <#list columns as column>
    /**
     * ${column.remarks}
     */
    <#if column.nullable><#if column.string>@NotBlank<#else>@NotNull</#if></#if>
    private ${column.javaType.simpleName} ${column.name};
  </#list>

}
