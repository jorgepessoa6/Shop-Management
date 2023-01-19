using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace SandwichManagement.Migrations
{
    /// <inheritdoc />
    public partial class InitDatabase : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Sandwiches",
                columns: table => new
                {
                    Id = table.Column<string>(type: "text", nullable: false),
                    Designation = table.Column<string>(type: "text", nullable: false),
                    Price = table.Column<double>(type: "double precision", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Sandwiches", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "SandwichDescriptions",
                columns: table => new
                {
                    Id = table.Column<string>(type: "text", nullable: false),
                    Description = table.Column<string>(type: "text", nullable: false),
                    Language = table.Column<string>(type: "text", nullable: true),
                    SandwichId = table.Column<string>(type: "text", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_SandwichDescriptions", x => x.Id);
                    table.ForeignKey(
                        name: "FK_SandwichDescriptions_Sandwiches_SandwichId",
                        column: x => x.SandwichId,
                        principalTable: "Sandwiches",
                        principalColumn: "Id");
                });

            migrationBuilder.CreateIndex(
                name: "IX_SandwichDescriptions_SandwichId",
                table: "SandwichDescriptions",
                column: "SandwichId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "SandwichDescriptions");

            migrationBuilder.DropTable(
                name: "Sandwiches");
        }
    }
}
