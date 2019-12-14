using Microsoft.EntityFrameworkCore.Migrations;

namespace Data.Migrations
{
    public partial class notification : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Notifications",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    AccountEmail = table.Column<string>(nullable: true),
                    Type = table.Column<string>(nullable: true),
                    ItemId = table.Column<int>(nullable: false),
                    Message = table.Column<string>(nullable: true),
                    Date = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Notifications", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Notifications_Accounts_AccountEmail",
                        column: x => x.AccountEmail,
                        principalTable: "Accounts",
                        principalColumn: "Email",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Notifications_AccountEmail",
                table: "Notifications",
                column: "AccountEmail");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Notifications");
        }
    }
}
