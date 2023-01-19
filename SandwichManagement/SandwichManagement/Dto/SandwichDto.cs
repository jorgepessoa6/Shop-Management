using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

    public class SandwichDto
    {
        [Required]
        [RegularExpression(@"^[a-zA-Z0-9]{1,40}$")]
        public string Designation { get; set; }
        [Required]
        [RegularExpression(@"^[1-9]\d*(\.\d{1,2})?$")]
        public float Price { get; set; }
        public List<SandwichDescriptionDto> SandwichDescriptionList { get; set; }
    }