using System.ComponentModel.DataAnnotations;

    public class SandwichDescriptionResponseDto
    {
            [Required]
            public string Description { get; set; }
            public string Language { get; set; }
    }