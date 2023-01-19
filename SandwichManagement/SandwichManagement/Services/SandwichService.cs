using Microsoft.EntityFrameworkCore;
using System.Threading.Tasks;
using System.Collections.Generic;
using System;
using Microsoft.Extensions.DependencyInjection;
using System.Linq;

namespace orderManagement.Services;

public class SandwichService : ISandwichService
{
    private readonly SandwichContext _context;
    private readonly ISandwichMapper _mapper;

    public SandwichService(SandwichContext context, IServiceProvider serviceProvider)
    {
        _context = context;
        _mapper = serviceProvider.GetRequiredService<ISandwichMapper>();
    }

    public async Task<SandwichResponseDto> SaveSandwich(SandwichDto sandwichDto)
    {
        Sandwich sandwich =_mapper.SandwichDtoToSandwich(sandwichDto);

        await _context.Sandwiches.AddAsync(sandwich);
        await _context.SaveChangesAsync();

        return _mapper.SandwichToSandwichResponseDto(sandwich);
    }
    public async Task<List<SandwichResponseDto>> GetSandwiches()
    {
        List<Sandwich> sandwiches = await _context.Sandwiches.Include(s => s.SandwichDescriptions).Select(s => s).ToListAsync();

        List<SandwichResponseDto> sandwichResponseDtos = new();
        foreach (Sandwich sdch in sandwiches)
        {
            sandwichResponseDtos.Add(_mapper.SandwichToSandwichResponseDto(sdch));
        }

        return sandwichResponseDtos;
    }

    public async Task<bool> DeleteSandwich(Sandwich sandwich)
    {
        try{
            _context.Sandwiches.Remove(sandwich);
            await _context.SaveChangesAsync();
            return true;
        }
        catch(Exception)
        {
            return false;
        }
    }

    public async Task<Sandwich> FindSandwichById(string sandwichId)
    {
        return await _context.Sandwiches.Include(s => s.SandwichDescriptions).Where(x => x.Id.Equals(sandwichId)).FirstOrDefaultAsync();
    }

    public async Task<List<string>> CommonSandwichIds(List<string> sandwichIds)
    {
        return await _context.Sandwiches.Where(s => sandwichIds.Contains(s.Id)).Select(s => s.Id).ToListAsync();
    }
}