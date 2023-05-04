using System.ComponentModel.DataAnnotations;

    public class SandwichDescriptionDto
    {
            [Required]
            [RegularExpression(@"^[a-zA-Z0-9]{1,20}$")]
            public string Description { get; set; }
            [RegularExpression(@"^[a-zA-Z0-9]{1,5}$")]
            public string Language { get; set; }
    }