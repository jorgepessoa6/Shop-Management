
     public interface ISandwichMapper
    {
        Sandwich SandwichDtoToSandwich(SandwichDto sandwichDto);

        SandwichResponseDto SandwichToSandwichResponseDto(Sandwich sandwich);

        SandwichDescriptionResponseDto SandwichDescToSandwichDescResponseDto(SandwichDescription sandwichDescription);
    }