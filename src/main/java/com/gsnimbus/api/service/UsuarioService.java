package com.gsnimbus.api.service;

import com.gsnimbus.api.dto.usuario.UsuarioDto;
import com.gsnimbus.api.dto.usuario.UsuarioMapper;
import com.gsnimbus.api.exception.ResourceNotFoundException;
import com.gsnimbus.api.model.Usuario;
import com.gsnimbus.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// É uma boa prática codificar senhas antes de salvá-las.
// import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
     private final PasswordEncoder passwordEncoder;

    @Cacheable(value = "findAllUsuario")
    @Transactional(readOnly = true)
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    @Cacheable(value = "findByIdUsuario", key = "#id")
    @Transactional(readOnly = true)
    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
    }

    @Transactional
    public Usuario save(UsuarioDto dto) {
        cleanCache();
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(UsuarioDto dto, Long id){
        cleanCache();
        Usuario usuario = findById(id);
         if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
             usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
         }
        usuarioMapper.updateEntityFromDto(dto, usuario);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(Long id){
        cleanCache();
        Usuario usuario = findById(id);
        usuarioRepository.delete(usuario);
    }

    @CacheEvict(value = {
            "findAllUsuario", "findByIdUsuario"
    }, allEntries = true)
    public void cleanCache(){
        System.out.println("Limpando cache de usuário...");
    }

}

