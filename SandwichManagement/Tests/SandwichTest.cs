using Xunit;

namespace Tests
{
    internal class SandwichTest
    {
        [Test]
        public void TestSandwich()
        {
            // Arrange
            var sandwich = new Sandwich
            {
                Id = "123",
                Designation = "A delicious sandwich",
                Price = 7.99,
                SandwichDescriptions = new List<SandwichDescription>
                {
                    new SandwichDescription
                    {
                        Description = "Turkey and Swiss",
                        Language = "en-US"
                    },
                    new SandwichDescription
                    {
                        Description = "Turquía y Suizo",
                        Language = "es-ES"
                    }
                }
            };

            // Act
            var id = sandwich.Id;
            var description = sandwich.Designation;
            var price = sandwich.Price;
            var descriptionSandwiches = sandwich.SandwichDescriptions;

            // Assert
            Assert.That(id, Is.EqualTo("123"));
            Assert.That(description, Is.EqualTo("A delicious sandwich"));
            Assert.That(price, Is.EqualTo(7.99));
            Assert.That(descriptionSandwiches.Count, Is.EqualTo(2));
            Assert.That(descriptionSandwiches[0].Description, Is.EqualTo("Turkey and Swiss"));
            Assert.That(descriptionSandwiches[0].Language, Is.EqualTo("en-US"));
            Assert.That(descriptionSandwiches[1].Description, Is.EqualTo("Turquía y Suizo"));
            Assert.That(descriptionSandwiches[1].Language, Is.EqualTo("es-ES"));
        }
    }
}
