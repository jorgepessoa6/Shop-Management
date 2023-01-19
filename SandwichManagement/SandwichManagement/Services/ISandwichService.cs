using System.Collections.Generic;
using System.Threading.Tasks;

namespace orderManagement.Services
{
    public interface ISandwichService
    {
        Task<bool> DeleteSandwich(Sandwich sandwich);
        Task<Sandwich> FindSandwichById(string sandwichId);
        Task<List<SandwichResponseDto>> GetSandwiches();
        Task<SandwichResponseDto> SaveSandwich(SandwichDto sandwichDto);
        Task<List<string>> CommonSandwichIds(List<string> sandwichIds);
    }
}