using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text.RegularExpressions;

namespace MyApplication.Attributes
{
    public class UuidListAttribute : ValidationAttribute
    {
        private static readonly Regex UuidPattern = new Regex(
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

        protected override ValidationResult IsValid(object value, ValidationContext validationContext)
        {
            var strings = (List<string>)value;
            foreach (var str in strings)
            {
                if (!UuidPattern.IsMatch(str))
                {
                    return new ValidationResult("One or more strings do not have the UUID pattern.");
                }
            }

            return ValidationResult.Success;
        }
    }
}
