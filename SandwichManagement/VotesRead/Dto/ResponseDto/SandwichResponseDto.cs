using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

    public class SandwichResponseDto
    {
        [Required]
        public string Id { get; set; }
        [Required]
        public string Designation { get; set; }
        [Required]
        public double Price { get; set; }
        public List<SandwichDescriptionResponseDto> SandwichDescriptionList { get; set; }
    }