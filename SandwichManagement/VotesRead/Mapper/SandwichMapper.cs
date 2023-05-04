using System;
using System.Collections.Generic;

public class SandwichMapper : ISandwichMapper
{
    public Sandwich SandwichDtoToSandwich(SandwichDto sandwichDto) {
        Sandwich sandwich = new Sandwich(){
            Id = Guid.NewGuid().ToString("D"),
            Designation = sandwichDto.Designation,
            Price = sandwichDto.Price
        };

        foreach (SandwichDescriptionDto sd in sandwichDto.SandwichDescriptionList)
        {
            sandwich.AddDescription(ToSandwichDescription(sd));
        }

        return sandwich;
    }

    private static SandwichDescription ToSandwichDescription(SandwichDescriptionDto sdDto) {
        SandwichDescription sandwichDescription = new SandwichDescription(){
            Id = Guid.NewGuid().ToString("D"),
            Description = sdDto.Description,
            Language = sdDto.Language
        };

        return sandwichDescription;
    }
    public SandwichResponseDto SandwichToSandwichResponseDto(Sandwich sandwich) {
        SandwichResponseDto sandwichResponseDto = new SandwichResponseDto(){
            Id = sandwich.Id,
            Designation = sandwich.Designation,
            Price = sandwich.Price
        };

        List<SandwichDescriptionResponseDto> sandwichDescriptionList = new List<SandwichDescriptionResponseDto>();
        foreach (SandwichDescription sandwichDescription in sandwich.SandwichDescriptions)
        {
            sandwichDescriptionList.Add(SandwichDescToSandwichDescResponseDto(sandwichDescription));
        }

        sandwichResponseDto.SandwichDescriptionList = sandwichDescriptionList;
        return sandwichResponseDto;
    }

    public SandwichDescriptionResponseDto SandwichDescToSandwichDescResponseDto(SandwichDescription sandwichDescription) {
        SandwichDescriptionResponseDto sandwichDescriptionResponseDto = new SandwichDescriptionResponseDto(){
            Description = sandwichDescription.Description,
            Language = sandwichDescription.Language
        };

        return sandwichDescriptionResponseDto;
    }
}