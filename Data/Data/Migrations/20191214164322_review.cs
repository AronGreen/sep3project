using Microsoft.EntityFrameworkCore.Migrations;

namespace Data.Migrations
{
    public partial class review : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Reviews",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    ReviewerEmail = table.Column<string>(nullable: true),
                    RevieweeEmail = table.Column<string>(nullable: true),
                    Content = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Reviews", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Reviews_Accounts_RevieweeEmail",
                        column: x => x.RevieweeEmail,
                        principalTable: "Accounts",
                        principalColumn: "Email",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Reviews_Accounts_ReviewerEmail",
                        column: x => x.ReviewerEmail,
                        principalTable: "Accounts",
                        principalColumn: "Email",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Reviews_RevieweeEmail",
                table: "Reviews",
                column: "RevieweeEmail");

            migrationBuilder.CreateIndex(
                name: "IX_Reviews_ReviewerEmail",
                table: "Reviews",
                column: "ReviewerEmail");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Reviews");
        }
    }
}
