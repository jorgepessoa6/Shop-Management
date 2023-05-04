using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

public class Sandwich
    {
        [Required]
        [Key]
        public string Id { get; set; }
        [Required]
        public string Designation { get; set; }
        [Required]
        public double Price { get; set; }
        [JsonIgnore]
        public virtual List<SandwichDescription> SandwichDescriptions { get; set; } = new List<SandwichDescription>();

        public void AddDescription(SandwichDescription sandwichDescription){
            SandwichDescriptions.Add(sandwichDescription);
        }
    }
