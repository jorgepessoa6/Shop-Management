using MyApplication.Attributes;
using System.Collections.Generic;

namespace SandwichManagement.Dto
{
    public class SandwichIdsDto
    {
        [UuidList]
        public List<string> SandwichIds { get; set; }
    }
}
