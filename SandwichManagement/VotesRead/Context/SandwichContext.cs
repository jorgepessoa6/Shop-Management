
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;

public class SandwichContext : DbContext
{
    public SandwichContext(DbContextOptions<SandwichContext> options) : base(options) {}

    public virtual DbSet<Sandwich> Sandwiches { get; set; }
    public virtual DbSet<SandwichDescription> SandwichDescriptions { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<SandwichDescription>()
                    .HasOne(sd => sd.Sandwich)
                    .WithMany(s => s.SandwichDescriptions)
                    .HasForeignKey(sd => sd.SandwichId);
    }
}