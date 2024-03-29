// Generated by Dagger (https://dagger.dev).
package com.valdosz.pokemon_list.di.module;

import com.valdosz.pokemon_list.model.api.PokemonAPI;
import com.valdosz.pokemon_list.model.repository.PokemonRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

@SuppressWarnings({
        "unchecked",
        "rawtypes"
})
public final class AppModule_PokemonRepositoryFactory implements Factory<PokemonRepository> {
    private final AppModule module;

    private final Provider<PokemonAPI> apiProvider;

    public AppModule_PokemonRepositoryFactory(AppModule module, Provider<PokemonAPI> apiProvider) {
        this.module = module;
        this.apiProvider = apiProvider;
    }

    @Override
    public PokemonRepository get() {
        return pokemonRepository(module, apiProvider.get());
    }

    public static AppModule_PokemonRepositoryFactory create(AppModule module,
                                                            Provider<PokemonAPI> apiProvider) {
        return new AppModule_PokemonRepositoryFactory(module, apiProvider);
    }

    public static PokemonRepository pokemonRepository(AppModule instance, PokemonAPI api) {
        return Preconditions.checkNotNullFromProvides(instance.pokemonRepository(api));
    }
}
