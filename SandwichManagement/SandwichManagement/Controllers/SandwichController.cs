using Microsoft.AspNetCore.Mvc;
using orderManagement.Services;
using System;
using Microsoft.Extensions.DependencyInjection;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using SandwichManagement.Dto;
using Microsoft.IdentityModel.Tokens;

[ApiController]
public class SandwichController : ControllerBase
{

    private readonly ISandwichService _sandwichService;

    public SandwichController(IServiceProvider serviceProvider)
    {
        _sandwichService = serviceProvider.GetRequiredService<ISandwichService>();
    }


    [HttpGet("sandwiches")]
    public async Task<IActionResult> GetSandwiches(){
        try
        {
            return Ok(await _sandwichService.GetSandwiches());
        }
        catch(Exception ex)
        {
            return BadRequest(ex.Message);
        }
    }

    [HttpGet("{sandwichId}")]
    public async Task<ActionResult<Sandwich>> FindSandwichId([FromRoute, 
        RegularExpression(@"^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")] string sandwichId)
    {
        try
        {
            var sandwich = await _sandwichService.FindSandwichById(sandwichId);

            if (sandwich == null)
            {
                return NotFound(sandwichId);
            }

            return sandwich;
        }
        catch(Exception ex)
        {
            return BadRequest(ex.Message);
        }
    }

    [HttpPost("addSandwich")]
    public async Task<ActionResult<SandwichResponseDto>> CreateSandwich([FromBody] SandwichDto sandwichDto)
    {
        // Validate the request body
        if (!ModelState.IsValid)
        {
            return BadRequest(ModelState);
        }

        try
        {
          return Ok(await _sandwichService.SaveSandwich(sandwichDto));
        }
        catch (Exception ex)
        {
            return BadRequest(ex.Message);
        }
    }

    [HttpDelete("sandwichId")]
    public async Task<ActionResult<bool>> DeleteSandwich([FromRoute,
        RegularExpression(@"^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")] string sandwichId)
    {
        // Get the sandwich from the database
        Sandwich sandwich = null;
        try
        {
            sandwich = await _sandwichService.FindSandwichById(sandwichId);

            if (sandwich == null)
            {
                return NotFound(sandwichId);
            }
        }
        catch(Exception x)
        {
            return BadRequest(x.Message);
        }    

        // Delete the sandwich from the database
        try
        {
            return Ok(await _sandwichService.DeleteSandwich(sandwich));
        }
        catch(Exception ex)
        {
            return BadRequest(ex.Message);
        }
    }

    [HttpPost("commonSandwichIds")]
    public async Task<ActionResult<SandwichIdsDto>> GetCommonSandwichIds([FromBody] SandwichIdsDto sandwichIdsDto)
    {
        if (!ModelState.IsValid)
        {
            return BadRequest(ModelState);
        } else if (sandwichIdsDto.SandwichIds.IsNullOrEmpty())
        {
            return NoContent();
        }

        try
        {
          return Ok(await _sandwichService.CommonSandwichIds(sandwichIdsDto.SandwichIds));
        }
        catch(Exception x)
        {
           return BadRequest(x.Message);
        }
    }
}
