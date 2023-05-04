using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

public class SandwichDescription
{
        [Required]
        [Key]
        public string Id { get; set; }
        [Required]
        public string Description { get; set; }
        public string Language { get; set; }

        [ForeignKey("Sandwich")]
        public string SandwichId { get; set; }
        [JsonIgnore]
        public virtual Sandwich Sandwich { get; set; }
}