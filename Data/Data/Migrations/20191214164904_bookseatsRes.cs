using Microsoft.EntityFrameworkCore.Migrations;

namespace Data.Migrations
{
    public partial class bookseatsRes : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "BookedSeats",
                table: "Reservations",
                nullable: false,
                defaultValue: 0);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "BookedSeats",
                table: "Reservations");
        }
    }
}
