using Microsoft.EntityFrameworkCore;
using Moq;
using orderManagement.Services;

namespace Tests
{
    internal class SandwichServiceTest
    {
        private readonly Mock<IServiceProvider> serviceProvider = new Mock<IServiceProvider>();
        private readonly SandwichService sandwichService;
        private readonly SandwichContext context;
        private readonly Sandwich deleteSandwich;
        public SandwichServiceTest() {

            var options = new DbContextOptionsBuilder<SandwichContext>()
                     .UseInMemoryDatabase("MyInMemoryDatabase")
                     .Options;

            context = new SandwichContext(options);

            deleteSandwich = new Sandwich { Id = "125", Designation = "Tuna Salad" };

            // Create a list of mock Sandwich objects
            var mockSandwiches = new List<Sandwich>
            {
                new Sandwich { Id = "123", Designation = "Grilled Cheese" },
                new Sandwich { Id = "124", Designation = "Turkey and Swiss" },
                deleteSandwich
            };

            context.Sandwiches.AddRangeAsync(mockSandwiches);
            context.SaveChangesAsync();

            // Create a mock DbContext
            var mapper = new SandwichMapper();
            serviceProvider.Setup(p => p.GetService(typeof(ISandwichMapper))).Returns(mapper);

            // Use the mock DbContext in your test
            sandwichService = new SandwichService(context, serviceProvider.Object);
        }

        [Test]
        public void TestGetSandwiches()
        {
            var sandwiches = sandwichService.GetSandwiches();
            Assert.That(sandwiches.Result, Has.Count.GreaterThan(1));
            Assert.That(sandwiches.Result[0].Designation, Is.EqualTo("Grilled Cheese"));
        }
        
        [Test]
        public void TestCreateSandwich()
        {
            var sandwiches = sandwichService.SaveSandwich(new SandwichDto { Designation = "An amazing sandwich" , Price = 20, SandwichDescriptionList = new()});
            Assert.That(sandwiches.Result.Designation, Is.EqualTo("An amazing sandwich"));
            Assert.That(sandwiches.Result.Id, Is.Not.Null);
        }

        [Test]
        public void TestDeleteSandwich()
        {
            Assert.That(sandwichService.DeleteSandwich(deleteSandwich).Result, Is.True);
        }

        [Test]
        public void TestFindSandwichById()
        {
            Assert.That(sandwichService.FindSandwichById("123").Result, Is.Not.Null);
        }
    }
}
