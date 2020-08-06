package com.senlainc.gitcourses.kashko.raman.serviceimpl;

import com.senlainc.gitcourses.kashko.raman.api.exceptions.LoginAlreadyExistsException;
import com.senlainc.gitcourses.kashko.raman.api.exceptions.LoginNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectIsFullException;
import com.senlainc.gitcourses.kashko.raman.api.exceptions.ObjectNotFoundException;
import com.senlainc.gitcourses.kashko.raman.api.repository.CourseRepository;
import com.senlainc.gitcourses.kashko.raman.api.repository.UserRepository;
import com.senlainc.gitcourses.kashko.raman.api.service.RoleService;
import com.senlainc.gitcourses.kashko.raman.api.service.UserService;
import com.senlainc.gitcourses.kashko.raman.dto.CourseShortDto;
import com.senlainc.gitcourses.kashko.raman.dto.LessonShortDto;
import com.senlainc.gitcourses.kashko.raman.dto.RoleDto;
import com.senlainc.gitcourses.kashko.raman.dto.UserDto;
import com.senlainc.gitcourses.kashko.raman.entity.Course;
import com.senlainc.gitcourses.kashko.raman.entity.Role;
import com.senlainc.gitcourses.kashko.raman.entity.RoleEnum;
import com.senlainc.gitcourses.kashko.raman.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private PasswordEncoder encoder;
    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private ModelMapper modelMapper;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, CourseRepository courseRepository, PasswordEncoder encoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.encoder = encoder;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public void addCoursesToUser(Integer userId, Integer courseId) throws ObjectNotFoundException, ObjectIsFullException {
        Course course = courseRepository.getAvailableCourseById(courseId);
        User user = userRepository.getById(userId);
        if (courseRepository.canAddUserInCourse(courseId)) {
            user.addCourse(course);
            modelMapper.map(user, UserDto.class);
            userRepository.update(user);
        } else {
            throw new ObjectIsFullException("Can't add user to this course");
        }
    }

    @Override
    public List<CourseShortDto> getCoursesOfUser(Integer userId) {
        return userRepository.getCoursesOfUser(userId).stream()
                .map(x -> modelMapper.map(x, CourseShortDto.class))
                .collect(Collectors.toList());
    }

    public List<LessonShortDto> getLessonsOfUser(Integer userId) {
        List<CourseShortDto> listCourses = getCoursesOfUser(userId);
        List<LessonShortDto> listLessons = new ArrayList<>();
        if (!listCourses.isEmpty()) {
            for (CourseShortDto listCourse : listCourses) {
                listLessons.addAll(listCourse.getLessons());
            }
        }
        return listLessons.stream()
                .map(x -> modelMapper.map(x, LessonShortDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Integer id) throws ObjectNotFoundException {
        User user = userRepository.getById(id);
        if (user == null) {
            throw new ObjectNotFoundException("no user with this id");
        }
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.getAll().stream()
                .map(x -> modelMapper.map(x, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(UserDto dto) throws LoginAlreadyExistsException{
        try {
            userRepository.getAccountByUsername(dto.getLogin());
            throw new LoginAlreadyExistsException("such login exists");
        }catch (LoginNotFoundException e)
        {
            String encodedPassword = encoder.encode(dto.getPassword());
            dto.setPassword(encodedPassword);
            RoleDto roleDto = roleService.getByName(RoleEnum.USER);
            List<RoleDto> roles = new ArrayList<>();
            roles.add(roleDto);
            dto.setRoles(roles);
            User user = modelMapper.map(dto, User.class);
            userRepository.save(user);
        }
    }

    @Override
    public UserDto update(Integer id, UserDto dto) throws ObjectNotFoundException {
        User user = userRepository.getById(id);
        dto.setId(user.getId());
        user = modelMapper.map(dto, User.class);
        return modelMapper.map(userRepository.update(user), UserDto.class);

    }

    @Override
    public Integer delete(Integer id) throws ObjectNotFoundException {
        userRepository.delete(userRepository.getById(id));
        return id;
    }

    @Override
    public UserDto getAccountByUsername(String username) {
        User user = userRepository.getAccountByUsername(username);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public boolean isPrincipalHasAdminRole(@AuthenticationPrincipal UserDetails userDetails) {
        boolean hasAdminRole = false;
        Collection<Role> roles = (Collection<Role>) userDetails.getAuthorities();
        for (Role role : roles) {
            if (role.getNameOfRole().equals(RoleEnum.ADMIN.name())) {
                hasAdminRole = true;
                break;
            }
        }
        return hasAdminRole;
    }

    @Override
    public boolean isAdminRoleContainsInDto(UserDto userDto) {
        List<RoleDto> roles = userDto.getRoles();
        return roles
                .stream()
                .anyMatch(role ->
                        role.getNameOfRole().equalsIgnoreCase(RoleEnum.ADMIN.name())
                );
    }

}
